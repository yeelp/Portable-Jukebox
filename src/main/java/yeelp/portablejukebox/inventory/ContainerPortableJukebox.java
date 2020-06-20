package yeelp.portablejukebox.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import yeelp.portablejukebox.PortableJukebox;
import yeelp.portablejukebox.item.PortableJukeboxItem;
import yeelp.portablejukebox.util.IPortableJukeboxSettings;
import yeelp.portablejukebox.util.PortableJukeboxSettingsProvider;

/**
 * Container for the Portable Jukebox
 * @author Yeelp
 *
 */
public class ContainerPortableJukebox extends Container 
{
	private final IPortableJukeboxSettings jukebox;
	private final int numRows;
	private final int yOffset;
	/**
	 * Construct a new ContainerPortableJukebox
	 * @param playerInventory the player's inventory
	 * @param jukebox the jukebox item
	 */
	public ContainerPortableJukebox(IInventory playerInventory, ItemStack jukebox)
	{
		this.jukebox = PortableJukeboxSettingsProvider.get(jukebox);
		PortableJukebox.debug(""+this.jukebox.getContents().getSlots());
		this.numRows = 1;
		this.yOffset = 17;//(this.numRows - 4) * 18;
		initSlots(playerInventory, this.jukebox.getContents());
	}
	
	public IPortableJukeboxSettings getSettings()
	{
		return this.jukebox;
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
                this.addSlotToContainer(new Slot(inv, col + row * 9 + 9, 160 + col * 18, 103 + row * 18 + yOffset));
            }
        }

        //Hotbar
        for (int hotbar = 0; hotbar < 9; hotbar++)
        {
            this.addSlotToContainer(new Slot(inv, hotbar, 160 + hotbar * 18, 161 + yOffset));
        }
	}

	private void addJukeboxSlots(IItemHandler item) 
	{
		for (int k = 0; k < 9; ++k)
	    {
	        this.addSlotToContainer(new SlotItemHandler(item, k, 160 + k * 18, 89));
	    }
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) 
	{
		return true;
	}
}
