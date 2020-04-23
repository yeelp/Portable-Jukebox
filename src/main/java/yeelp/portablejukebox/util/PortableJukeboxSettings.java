package yeelp.portablejukebox.util;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.annotation.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.items.IItemHandler;
import yeelp.portablejukebox.util.PlayConfiguration.PlayStyle;
import yeelp.portablejukebox.util.PlayConfiguration.RepeatStyle;

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
	private boolean isPlaying = false;
	private PortableMusic nowPlaying;
	
	public PortableJukeboxSettings()
	{
		inv = new PortableJukeboxInventory();
		playConfiguration = new PlayConfiguration(PlayStyle.NORMAL, RepeatStyle.NONE);
	}
	
	@Override
	public void update()
	{
		List<ItemStack> temp = new LinkedList<ItemStack>();
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
	public void play(EntityPlayer player)
	{
		ItemStack next = this.getNextTrack();
		if(!next.isEmpty())
		{
			nowPlaying = new PortableMusic(player, getSoundEvent(this.getNextTrack()));
			Minecraft.getMinecraft().getSoundHandler().stopSounds();
			Minecraft.getMinecraft().getSoundHandler().playSound(nowPlaying);
			isPlaying = true;
		}
	}
	
	@Override
	public void stop()
	{
		if(!nowPlaying.isDonePlaying())
		{
			Minecraft.getMinecraft().getSoundHandler().stopSound(nowPlaying);
			isPlaying = false;
		}
	}
	
	@Override
	public PortableMusic getMusicPlaying()
	{
		return this.nowPlaying;
	}
	
	@Override
	public boolean isPlaying()
	{
		return this.isPlaying;
	}
	
	private SoundEvent getSoundEvent(ItemStack nextTrack) 
	{
		return inv.getSoundEvent(nextTrack);
	}

	@Override
	public PlayConfiguration getPlayConfiguration()
	{
		return this.playConfiguration;
	}
	
	@Override
	@Nullable
	public ItemStack getNextTrack()
	{
		PlayConfiguration pc = this.getPlayConfiguration();
	   	ItemStack next = ItemStack.EMPTY;
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
		return String.format("%s, %s, %s", playConfiguration.getPlayStyle().toString(), playConfiguration.getRepeatStyle().toString(), this.queue.toString());
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) 
	{
		return capability == PortableJukeboxSettingsProvider.pjbs;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) 
	{
		return capability == PortableJukeboxSettingsProvider.pjbs ? PortableJukeboxSettingsProvider.pjbs.<T> cast(this) : null;
	}

	@Override
	public NBTTagCompound serializeNBT() 
	{
		NBTTagCompound tag = new NBTTagCompound();
		tag.setTag("contents", inv.serializeNBT());
		tag.setTag("configuration", playConfiguration.serializeNBT());
		return tag;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) 
	{
		inv.deserializeNBT(nbt.getCompoundTag("contents"));
		playConfiguration.deserializeNBT(nbt.getCompoundTag("configuration"));
	}

	@Override
	public IItemHandler getContents() 
	{
		return this.inv;
	}
	
	/**
	 * Register this capability
	 */
	public static void register()
	{
		CapabilityManager.INSTANCE.register(IPortableJukeboxSettings.class, new SettingsStorage(), new SettingsFactory());
	}

	private static class SettingsFactory implements Callable<IPortableJukeboxSettings>
	{
		@Override
		public IPortableJukeboxSettings call() throws Exception 
		{
			return new PortableJukeboxSettings();
		}
	}
	
	private static class SettingsStorage implements IStorage<IPortableJukeboxSettings>
	{

		@Override
		public NBTBase writeNBT(Capability<IPortableJukeboxSettings> capability, IPortableJukeboxSettings instance, EnumFacing side) 
		{
			return instance.serializeNBT();
		}

		@Override
		public void readNBT(Capability<IPortableJukeboxSettings> capability, IPortableJukeboxSettings instance, EnumFacing side, NBTBase nbt) 
		{
			if(nbt instanceof NBTTagCompound)
			{
				instance.deserializeNBT((NBTTagCompound) nbt);
			}
		}
		
	}
}
