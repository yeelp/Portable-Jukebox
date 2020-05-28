package yeelp.portablejukebox.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import net.minecraft.init.Items;
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
	 * Build a new Music Queue
	 */
	public MusicQueue()
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
	
	/**
	 * Shuffle the queue.
	 */
	public void shuffle()
	{
		ArrayList<ItemStack> lst = new ArrayList<ItemStack>();
		lst.addAll(queue);
		Collections.shuffle(lst, new Random(System.currentTimeMillis()));
		queue.clear();
		for (ItemStack itemStack : lst)
		{
			enqueue(itemStack);
		}
	}
	
	@Override
	public String toString()
	{
		String str = "[";
		for(ItemStack i : queue)
		{
			if(str.length() != 1)
			{
				str += ", ";
			}
			str += i.getItem().getUnlocalizedName();
		}
		return str + "]";
	}
}
