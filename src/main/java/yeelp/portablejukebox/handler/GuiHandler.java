package yeelp.portablejukebox.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.items.IItemHandler;
import yeelp.portablejukebox.gui.PortableJukeboxGui;
import yeelp.portablejukebox.inventory.ContainerPortableJukebox;
import yeelp.portablejukebox.item.PortableJukeboxItem;

public class GuiHandler implements IGuiHandler
{
	public static final int PORTABLEJUKEBOX_ID = 0;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		switch(ID)
		{
			case PORTABLEJUKEBOX_ID:
				return new ContainerPortableJukebox(player.inventory, getJukebox(player));
			default:
				return null;
		}
	}

	private ItemStack getJukebox(EntityPlayer player) 
	{
		if(player.getHeldItemMainhand().getItem() instanceof PortableJukeboxItem)
		{
			return player.getHeldItemMainhand();
		}
		else
		{
			return player.getHeldItemOffhand();
		}
	}
	
	private EnumHand getHandWithJukebox(EntityPlayer player)
	{
		if(player.getHeldItemMainhand().getItem() instanceof PortableJukeboxItem)
		{
			return EnumHand.MAIN_HAND;
		}
		else
		{
			return EnumHand.OFF_HAND;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		switch(ID)
		{
			case PORTABLEJUKEBOX_ID:
				return new PortableJukeboxGui(player.inventory, new ContainerPortableJukebox(player.inventory, player.getHeldItem(getHandWithJukebox(player))));
			default:
				return null;
		}
	}
	
}
