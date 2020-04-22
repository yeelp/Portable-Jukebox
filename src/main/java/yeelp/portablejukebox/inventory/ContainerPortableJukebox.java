package yeelp.portablejukebox.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

/**
 * Container for the Portable Jukebox
 * @author Yeelp
 *
 */
public class ContainerPortableJukebox extends Container 
{
	private final IItemHandler jukebox;
	private final int numRows;
	private final int yOffset;
	/**
	 * Construct a new ContainerPortableJukebox
	 * @param playerInventory the player's inventory
	 * @param jukebox the jukebox item
	 */
	public ContainerPortableJukebox(IInventory playerInventory, ItemStack jukebox)
	{
		this.jukebox = (IItemHandler)jukebox.getItem();
		this.numRows = 1;
		this.yOffset = (this.numRows - 4) * 18;
		initSlots(playerInventory, this.jukebox);
	}
	
	private void initSlots(IInventory inv, IItemHandler item)
	{
		addJukeboxSlots(item);
		addPlayerSlots(inv);
	}
	
	private void addPlayerSlots(IInventory inv) 
	{
		for (int row = 0; row < 3; row++)
        {
            for (int col = 0; col < 9; col++)
            {
                this.addSlotToContainer(new Slot(inv, col + row * 9 + 9, 8 + col * 18, 103 + row * 18 + yOffset));
            }
        }

        //Hotbar
        for (int hotbar = 0; hotbar < 9; hotbar++)
        {
            this.addSlotToContainer(new Slot(inv, hotbar, 8 + hotbar * 18, 161 + yOffset));
        }
	}

	private void addJukeboxSlots(IItemHandler item) 
	{
		for (int j = 0; j < this.numRows; ++j)
	    {
			for (int k = 0; k < 9; ++k)
	        {
	            this.addSlotToContainer(new SlotItemHandler(item, k + j * 9, 8 + k * 18, 18 + j * 18));
	        }
	    }
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) 
	{
		return true;
	}
}
