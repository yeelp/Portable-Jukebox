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
	private NonNullMap<Integer, ItemStack> contents;
	
	public PortableJukeboxInventory()
	{
		super(9);
		filter = new MultiFilter(ItemRecord.class);
		contents = new NonNullMap(ItemStack.EMPTY, 0, 1, 2, 3, 4, 5, 6, 7, 8);
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
		return contents.values();
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
		if(clazz != null)
		{
			switch(clazz.getSimpleName())
			{
				case "ItemRecord":
					return ((ItemRecord) stack.getItem()).getSound();
				default:
					return null;
			}
		}
		else
		{
			return null;
		}
	}
	
	@Override
	public void setStackInSlot(int slot, @Nonnull ItemStack stack)
	{
		if(isItemValid(slot, stack))
		{
			contents.put(slot, stack);
		}
		super.setStackInSlot(slot, stack);
	}
	
	@Override
	@Nonnull
	public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate)
	{
		if(isItemValid(slot, stack) && !simulate)
		{
			contents.put(slot, stack);
		}
		return super.insertItem(slot, stack, simulate);
	}
	
	@Override
	@Nonnull
	public ItemStack extractItem(int slot, @Nonnull int amount, boolean simulate)
	{
		if(!simulate && amount == 1)
		{
			contents.setDefault(slot);
		}
		return super.extractItem(slot, amount, simulate);
	}
}
