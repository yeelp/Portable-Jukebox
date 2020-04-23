package yeelp.portablejukebox.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.items.IItemHandler;


/**
 * Jukebox settings capability
 * @author Yeelp
 *
 */
public interface IPortableJukeboxSettings extends ICapabilitySerializable<NBTTagCompound>
{
	/**
	 * Get the inventory
	 * @return the inventory
	 */
	IItemHandler getContents();
	
	/**
	 * Get the next track
	 * @return the next track
	 */
	ItemStack getNextTrack();
	
	/**
	 * Get the play configuration
	 * @return the play configuration
	 */
	PlayConfiguration getPlayConfiguration();
	
	/**
	 * Update the settings
	 */
	void update();
	
	/**
	 * Play music for this player
	 * @param player player to play music for.
	 */
	void play(EntityPlayer player);
	
	/**
	 * Stop music
	 */
	void stop();
	
	/**
	 * Is there music playing?
	 * @return true if there is music playing. False otherwise
	 */
	boolean isPlaying();
	
	/**
	 * Get currently playing music
	 * @return music playing
	 */
	PortableMusic getMusicPlaying();
}
