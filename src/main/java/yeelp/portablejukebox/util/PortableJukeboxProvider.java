package yeelp.portablejukebox.util;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import yeelp.portablejukebox.item.PortableJukeboxItem;

public class PortableJukeboxProvider implements ICapabilityProvider, ICapabilitySerializable {

	private final PortableJukeboxInventory inventory;
	
	
	
	public PortableJukeboxProvider()
	{
		inventory = new PortableJukeboxInventory();
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) 
	{
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) 
	{
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T) inventory : null;
	}

	@Override
	public NBTBase serializeNBT() 
	{
		return inventory.serializeNBT();
	}

	@Override
	public void deserializeNBT(NBTBase nbt)
	{
		inventory.deserializeNBT((NBTTagCompound) nbt);
	}

}
