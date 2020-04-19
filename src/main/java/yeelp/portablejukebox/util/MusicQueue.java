package yeelp.portablejukebox.util;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

import net.minecraft.item.ItemStack;

/**
 * A Queue of Music
 * @author Yeelp
 *
 */
public class MusicQueue 
{
	private Queue<ItemStack> queue;
	/**
	 * Build a Music Queue from the items given
	 * @param A Collection of ItemStacks, which will be records that can be played.
	 */
	public MusicQueue(Collection<ItemStack> items)
	{
		queue = new LinkedList<ItemStack>();
	}
	/**
	 * Dequeue an item
	 * @return The item dequeued. Can be null if the queue is empty.
	 */
	public ItemStack dequeue()
	{
		return queue.poll();
	}
	/**
	 * Get the head of the queue. This is used for single repeat
	 * @return the head of the queue. May be null.
	 */
	public ItemStack peek()
	{
		return queue.peek();
	}
	/**
	 * Enqueue an item
	 * @param stack the item stack to enqueue
	 * @return true if the item stack was enqueued, false otherwise.
	 */
	public boolean enqueue(ItemStack stack)
	{
		return queue.offer(stack);
	}
	/**
	 * Get the size of the Music Queue
	 * @return the size of the Music Queue
	 */
	public int size()
	{
		return queue.size();
	}
}
