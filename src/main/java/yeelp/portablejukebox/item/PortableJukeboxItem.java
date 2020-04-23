package yeelp.portablejukebox.item;

import javax.annotation.Nullable;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.items.IItemHandler;
import yeelp.portablejukebox.ModConsts;
import yeelp.portablejukebox.PortableJukebox;
import yeelp.portablejukebox.handler.GuiHandler;
import yeelp.portablejukebox.util.MultiFilter;
import yeelp.portablejukebox.util.NonNullMap;
import yeelp.portablejukebox.util.PortableJukeboxProvider;
import yeelp.portablejukebox.util.PortableJukeboxSettings;
import yeelp.portablejukebox.util.PortableJukeboxSettings.PlayStyle;
import yeelp.portablejukebox.util.PortableJukeboxSettings.RepeatStyle;

public class PortableJukeboxItem extends Item
{
	private PortableJukeboxSettings settings;
	
	public PortableJukeboxItem()
	{
		this.setRegistryName("portablejukebox");
		this.setUnlocalizedName(ModConsts.MODID + ".portablejukebox");
		this.setCreativeTab(CreativeTabs.MISC);
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
			playerIn.openGui(PortableJukebox.instance, GuiHandler.PORTABLEJUKEBOX_ID, playerIn.world, 0, 0, 0);
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItemMainhand());
		}
		else
		{
			//TODO When right clicked and NOT sneaking, setup a PortableJukeboxSettings object, and play the music 
			settings = new PortableJukeboxSettings(new PlayConfiguration(), );
			PortableJukebox.debug(settings.toString());
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItemMainhand());
		}
	}
	
	/**
	 * Register this item
	 */
	public static void register()
	{
		ForgeRegistries.ITEMS.register(new PortableJukeboxItem());
	}
}
