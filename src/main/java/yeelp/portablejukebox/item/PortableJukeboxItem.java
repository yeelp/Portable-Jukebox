package yeelp.portablejukebox.item;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import yeelp.portablejukebox.util.MultiFilter;
import yeelp.portablejukebox.util.NonNullMap;
import yeelp.portablejukebox.util.PortableJukeboxSettings;

import static yeelp.portablejukebox.util.PortableJukeboxSettings.PlayStyle;
import static yeelp.portablejukebox.util.PortableJukeboxSettings.RepeatStyle;

public class PortableJukeboxItem extends Item implements IItemHandler
{
	private PortableJukeboxSettings settings;
	private PlayStyle playStyle;
	private RepeatStyle repeatStyle;
	private NonNullMap<Integer, ItemStack> contents;
	private static MultiFilter filter;
	
	public PortableJukeboxItem()
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
		return 12;
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
				ItemStack curr = getStackInSlot(slot);
				contents.put(slot, stack);
				return curr;
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
		return contents.get(slot);
	}

	@Override
	public int getSlotLimit(int slot) 
	{
		return 1;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		if(playerIn.isSneaking())
		{
			//TODO Open GUI and let player modify discs, play settings, and stuff like that
		}
		else
		{
			//TODO When right clicked and NOT sneaking, setup a PortableJukeboxSettings object, and play the music 
			settings = new PortableJukeboxSettings(playStyle, repeatStyle, contents.values());
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItemMainhand());
		}
	}
	
	public static void register()
	{
		
	}
}
