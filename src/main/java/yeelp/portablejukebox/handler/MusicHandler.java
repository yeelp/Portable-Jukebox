package yeelp.portablejukebox.handler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import yeelp.portablejukebox.PortableJukebox;
import yeelp.portablejukebox.item.PortableJukeboxItem;
import yeelp.portablejukebox.util.IPortableJukeboxSettings;
import yeelp.portablejukebox.util.PortableJukeboxSettingsProvider;
import yeelp.portablejukebox.util.PortableMusic;

public class MusicHandler extends Handler 
{
	private static final Map<UUID, IPortableJukeboxSettings> playingJukeboxes = new HashMap<UUID, IPortableJukeboxSettings>();
	private static final Map<UUID, Integer> tickMap = new HashMap<UUID, Integer>();
	@SubscribeEvent
	public void tick(PlayerTickEvent evt)
	{
		if(tick(evt.player) % 100 == 0)
		{
			IPortableJukeboxSettings jukebox = this.getPlayingJukebox(evt.player);
			PortableJukebox.debug("jukebox null? " + (jukebox == null));
			if(jukebox != null)
			{
				PortableJukebox.debug("Jukebox Playing? "+jukebox.isPlaying());
				if(jukebox.isPlaying())
				{
					PortableJukebox.debug("track Playing? "+Minecraft.getMinecraft().getSoundHandler().isSoundPlaying(jukebox.getMusicPlaying()));
				}
				if(!jukebox.isPlaying() || (jukebox.isPlaying() && !Minecraft.getMinecraft().getSoundHandler().isSoundPlaying(jukebox.getMusicPlaying())))
				{
					jukebox.play(evt.player);
				}
			}
		}
	}

	@Nullable
	private IPortableJukeboxSettings getPlayingJukebox(EntityPlayer player) 
	{
		return playingJukeboxes.get(player.getUniqueID());
	}
	
	private int tick(EntityPlayer player)
	{
		Integer tick = tickMap.get(player.getUniqueID());
		if(tick == null)
		{
			tickMap.put(player.getUniqueID(), 0);
			return 0;
		}
		else
		{
			tickMap.put(player.getUniqueID(), ++tick);
			return tick;
		}
	}
	
	/**
	 * Stop all other jukeboxes for a player
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
	
	/**
	 * Update the MusicHandler with the IPortableJukeboxSettings that are playing for a player
	 * @param playerIn player
	 * @param settings settings that are currently playing for a player. Null represents no jukebox playing for playerIn.
	 */
	public static void updatePlayingJukebox(EntityPlayer playerIn, IPortableJukeboxSettings settings)
	{
		playingJukeboxes.put(playerIn.getUniqueID(), settings);
		
	}
}
