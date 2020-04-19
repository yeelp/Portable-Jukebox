package yeelp.portablejukebox.util;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.item.ItemStack;

/**
 * Simple container to store portable jukebox settings
 * @author Yeelp
 *
 */
public class PortableJukeboxSettings 
{
	/**
	 * The different play styles of the portable jukebox. Can be NORMAL or SHUFFLE
	 * @author Yeelp
	 *
	 */
	public enum PlayStyle
	{
		NORMAL,
		SHUFFLE;
	}
	
	/**
	 * The Different Repeat styles of the portable jukebox. Can be NONE, ALL, or SINGLE
	 * @author Yeelp
	 *
	 */
	public enum RepeatStyle
	{
		NONE,
		ALL,
		SINGLE;
	}
	
	private PlayStyle playStyle;
	private RepeatStyle repeatStyle;
	private MusicQueue queue;
	/**
	 * Setup a new PortableJukeboxSettings
	 * @param playStyle The play style. NORMAL or SHUFFLE
	 * @param repeatStyle The repeat style. NONE, ALL or SINGLE
	 * @param tracks the list of contents from the PortableJukebox. Empty entries will be parsed out be the constructor.
	 */
	public PortableJukeboxSettings(PlayStyle playStyle, RepeatStyle repeatStyle, Collection<ItemStack> tracks)
	{
		this.playStyle = playStyle;
		this.repeatStyle = repeatStyle;
		List<ItemStack> temp = new LinkedList<ItemStack>();
		//We can't modify the tracks Collection directly, as it is backed by the NonNullMap; changes will be reflected in the map.
		//So we copy the references over (which is fine to do), then shuffle if needed.
		for(ItemStack stack : tracks)
		{
			if(!stack.isEmpty())
			{
				temp.add(stack);
			}
		}
		if(playStyle == PlayStyle.SHUFFLE)
		{
			Collections.shuffle(temp);
		}
		this.queue = new MusicQueue(temp);
	}
	/**
	 * Get the next track to play
	 * @return the next Item Stack specified by this PortableJukeboxSettings
	 */
	public ItemStack getNextTrack()
	{
	   	ItemStack next = null;
		if(this.queue.size() == 0)
		{
			return ItemStack.EMPTY;
		}
		switch(this.repeatStyle)
		{
			case SINGLE:
				next = this.queue.peek();
				break;
			case NONE:
				next = queue.dequeue();
				break;
			case ALL:
				next = queue.dequeue();
				queue.enqueue(next);
				
		}
		return next;
	}
}
