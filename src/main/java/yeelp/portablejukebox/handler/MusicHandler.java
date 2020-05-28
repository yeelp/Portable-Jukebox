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
	private int tick = 0;
	@SubscribeEvent
	public void tick(PlayerTickEvent evt)
	{
		tick++;
		IPortableJukeboxSettings jukebox = this.getPlayingJukebox(evt.player);
		if(tick % 100 == 0)
		{
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

	public static void updatePlayingJukebox(EntityPlayer playerIn, IPortableJukeboxSettings settings)
	{
		playingJukeboxes.put(playerIn.getUniqueID(), settings);
	}
}
