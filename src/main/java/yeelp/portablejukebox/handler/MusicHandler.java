package yeelp.portablejukebox.handler;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import yeelp.portablejukebox.item.PortableJukeboxItem;
import yeelp.portablejukebox.util.IPortableJukeboxSettings;
import yeelp.portablejukebox.util.PortableJukeboxSettingsProvider;

public class MusicHandler extends Handler 
{
	@SubscribeEvent
	public void tick(PlayerTickEvent evt)
	{
		IPortableJukeboxSettings jukebox = this.getPlayingJukebox(evt.player);
		if(jukebox != null)
		{
			if(!jukebox.isPlaying() && jukebox.getMusicPlaying().isDonePlaying())
			{
				jukebox.play(evt.player);
			}
		}
	}

	@Nullable
	private IPortableJukeboxSettings getPlayingJukebox(EntityPlayer player) 
	{
		for(ItemStack i : player.inventory.mainInventory)
		{
			if(i.getItem() instanceof PortableJukeboxItem)
			{
				IPortableJukeboxSettings settings = PortableJukeboxSettingsProvider.get(i);
				if(settings.isPlaying())
				{
					return settings;
				}
			}
		}
		if(player.getHeldItemOffhand().getItem() instanceof PortableJukeboxItem)
		{
			IPortableJukeboxSettings settings = PortableJukeboxSettingsProvider.get(player.getHeldItemOffhand());
			if(settings.isPlaying())
			{
				return settings;
			}
		}
		return null;
	}
	
	/**
	 * Stop all other jukeboxes player for a player
	 * @param player player to target
	 */
	public static void stopAllJukeboxes(EntityPlayer player)
	{
		for(ItemStack i : player.inventory.mainInventory)
		{
			if(i.getItem() instanceof PortableJukeboxItem)
			{
				PortableJukeboxSettingsProvider.get(i).stop();
			}
		}
		if(player.getHeldItemOffhand().getItem() instanceof PortableJukeboxItem)
		{
			PortableJukeboxSettingsProvider.get(player.getHeldItemOffhand()).stop();
		}
	}
}
