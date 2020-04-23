package yeelp.portablejukebox.util;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.IItemHandler;
import yeelp.portablejukebox.util.PlayConfiguration.PlayStyle;

/**
 * Simple container to store portable jukebox settings
 * @author Yeelp
 *
 */
public class PortableJukeboxSettings implements IPortableJukeboxSettings
{
	private MusicQueue queue;
	private PlayConfiguration playConfiguration;
	private PortableJukeboxInventory inv;
	
	public PortableJukeboxSettings()
	{
		
	}
	
	/**
	 * Create a new PortableJukeboxSettings
	 * @param pc The PlayConfiguration
	 * @param inv The PortableJukeboxInventory
	 */
	public PortableJukeboxSettings(PlayConfiguration pc, PortableJukeboxInventory inv)
	{
		this.inv = inv;
		this.playConfiguration = pc;
		List<ItemStack> temp = new LinkedList<ItemStack>();
		//We can't modify the tracks Collection directly, as it is backed by the NonNullMap; changes will be reflected in the map.
		//So we copy the references over (which is fine to do), then shuffle if needed.
		for(ItemStack stack : inv.items())
		{
			if(!stack.isEmpty())
			{
				temp.add(stack);
			}
		}
		if(pc.getPlayStyle() == PlayStyle.SHUFFLE)
		{
			Collections.shuffle(temp);
		}
		this.queue = new MusicQueue(temp);
	}
	
	public void update()
	{
		List<ItemStack> temp = new LinkedList<ItemStack>();
		//We can't modify the tracks Collection directly, as it is backed by the NonNullMap; changes will be reflected in the map.
		//So we copy the references over (which is fine to do), then shuffle if needed.
		for(ItemStack stack : inv.items())
		{
			if(!stack.isEmpty())
			{
				temp.add(stack);
			}
		}
		if(this.getPlayConfiguration().getPlayStyle() == PlayStyle.SHUFFLE)
		{
			Collections.shuffle(temp);
		}
		this.queue = new MusicQueue(temp);
	}
	
	@Override
	public PlayConfiguration getPlayConfiguration()
	{
		return this.playConfiguration;
	}
	
	@Override
	public ItemStack getNextTrack()
	{
		PlayConfiguration pc = this.getPlayConfiguration();
	   	ItemStack next = null;
		if(this.queue.size() == 0)
		{
			return ItemStack.EMPTY;
		}
		switch(pc.getRepeatStyle())
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
	
	@Override
	public String toString()
	{
		return String.format("%s, %s, %s", this.getPlayConfiguration().getPlayStyle().toString(), this.getPlayConfiguration().getRepeatStyle().toString(), this.queue.toString());
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) 
	{
		return capability == PortableJukeboxSettingsProvider.pjbs;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NBTTagCompound serializeNBT() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IItemHandler getContents() 
	{
		return this.inv;
	}
}
