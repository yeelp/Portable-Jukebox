package yeelp.portablejukebox.util;

import java.util.Collection;

import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class PortableJukeboxInventory extends ItemStackHandler implements IItemHandler
{
	private NonNullMap<Integer, ItemStack> contents;
	private static MultiFilter filter;
	
	public PortableJukeboxInventory()
	{
		Integer[] slots = new Integer[getSlots()];
		for(int i = 0; i < getSlots(); i++)
		{
			slots[i] = i;
		}
		contents = new NonNullMap(ItemStack.EMPTY, (Object[])slots);
		filter = new MultiFilter(ItemRecord.class);
	}
	
	@Override
	public int getSlots() 
	{
		return 9;
	}

	@Override
	public ItemStack getStackInSlot(int slot) 
	{
		return contents.get(slot);
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) 
	{
		if(filter.validate(stack.getItem()))
		{
			if(getStackInSlot(slot).isEmpty())
			{
				contents.put(slot, stack);
				return ItemStack.EMPTY;
			}
			else
			{
				ItemStack inSlot = getStackInSlot(slot);
				contents.put(slot, stack);
				return inSlot;
			}
		}
		else
		{
			return stack;
		}
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) 
	{
		return contents.setDefault(slot);
	}

	@Override
	public int getSlotLimit(int slot) 
	{
		return 1;
	}
	
	public Collection<ItemStack> items()
	{
		return contents.values();
	}
}
