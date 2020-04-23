package yeelp.portablejukebox.util;

import java.util.Collection;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class PortableJukeboxInventory extends ItemStackHandler
{
	private static MultiFilter filter;
	
	public PortableJukeboxInventory()
	{
		super(9);
		filter = new MultiFilter(ItemRecord.class);
	}

	@Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack)
    {
        return filter.validate(stack.getItem());
    }
	
	@Override
	public int getSlotLimit(int slot) 
	{
		return 1;
	}
	
	/**
	 * Get a list of all the items in the inventory
	 * @return a Collection of ItemStacks that represents this inventory.
	 */
	public Collection<ItemStack> items()
	{
		return super.stacks;
	}

	/**
	 * Get the SoundEvent for an item
	 * @param stack item stack to get sound event for
	 * @return sound event for the item, or null if there is no sound event.
	 */
	@Nullable
	public SoundEvent getSoundEvent(ItemStack stack) 
	{
		Class clazz = filter.classify(stack.getItem());
		switch(clazz.getSimpleName())
		{
			case "ItemRecord":
				return ((ItemRecord) stack.getItem()).getSound();
			default:
				return null;
		}
	}
}
