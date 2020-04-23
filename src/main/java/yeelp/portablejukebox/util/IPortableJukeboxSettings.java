package yeelp.portablejukebox.util;

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
}
