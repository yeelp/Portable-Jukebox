package yeelp.portablejukebox.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.items.IItemHandler;
import yeelp.portablejukebox.gui.PortableJukeboxGui;
import yeelp.portablejukebox.inventory.ContainerPortableJukebox;

public class GuiHandler implements IGuiHandler
{
	public static final int PORTABLEJUKEBOX_ID = 0;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		switch(ID)
		{
			case PORTABLEJUKEBOX_ID:
				return new ContainerPortableJukebox(player.inventory, player.getHeldItemMainhand());
			default:
				return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		switch(ID)
		{
			case PORTABLEJUKEBOX_ID:
				return new PortableJukeboxGui(player.inventory, new ContainerPortableJukebox(player.inventory, player.getHeldItemMainhand()));
			default:
				return null;
		}
	}
	
}
