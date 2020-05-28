package yeelp.portablejukebox.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import yeelp.portablejukebox.ModConsts;
import yeelp.portablejukebox.PortableJukebox;
import yeelp.portablejukebox.handler.GuiHandler;
import yeelp.portablejukebox.handler.MusicHandler;
import yeelp.portablejukebox.util.IPortableJukeboxSettings;
import yeelp.portablejukebox.util.PortableJukeboxSettings;
import yeelp.portablejukebox.util.PortableJukeboxSettingsProvider;

public class PortableJukeboxItem extends Item
{	
	private static final PortableJukeboxItem INSTANCE = new PortableJukeboxItem();
	public PortableJukeboxItem()
	{
		this.setRegistryName("portablejukebox");
		this.setUnlocalizedName(ModConsts.MODID + ".portablejukebox");
		this.setCreativeTab(CreativeTabs.MISC);
		this.setMaxStackSize(1);
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add("Use while sneaking to add/remove records.");
		tooltip.add("Use normally to play/stop music");
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt)
	{
		if(stack.getItem() instanceof PortableJukeboxItem)
		{
			return new PortableJukeboxSettings();
		}
		return null;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		if(playerIn.isSneaking())
		{
			IPortableJukeboxSettings settings = PortableJukeboxSettingsProvider.get(playerIn.getHeldItem(handIn));
			if(settings.isPlaying())
			{
				settings.stop();
			}
			playerIn.openGui(PortableJukebox.instance, GuiHandler.PORTABLEJUKEBOX_ID, playerIn.world, 0, 0, 0);
			settings.update();
			PortableJukebox.debug(settings.toString());
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
		}
		else
		{
			if(!worldIn.isRemote)
			{
				IPortableJukeboxSettings settings = PortableJukeboxSettingsProvider.get(playerIn.getHeldItem(handIn));
				settings.update();
				PortableJukebox.debug(((PortableJukeboxSettings) settings).toString());
				if(settings.isPlaying())
				{
					settings.stop();
					MusicHandler.updatePlayingJukebox(playerIn, null);
				}
				else
				{
					MusicHandler.stopAllJukeboxes(playerIn);
					settings.play(playerIn);
					MusicHandler.updatePlayingJukebox(playerIn, settings);
				}
			}
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
		}
	}
	
	/**
	 * Register this item
	 */
	public static void register()
	{
		ForgeRegistries.ITEMS.register(INSTANCE);
	}
	
	public static void registerRender()
	{
		ModelLoader.setCustomModelResourceLocation(INSTANCE,  0, new ModelResourceLocation(INSTANCE.getRegistryName(), "inventory"));
	}
}
