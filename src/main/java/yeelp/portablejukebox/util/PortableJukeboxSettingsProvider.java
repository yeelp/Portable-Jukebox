package yeelp.portablejukebox.util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class PortableJukeboxSettingsProvider 
{
	@CapabilityInject(IPortableJukeboxSettings.class)
	public static Capability<IPortableJukeboxSettings> pjbs = null;
	
	private IPortableJukeboxSettings instance =  pjbs.getDefaultInstance();
	
	public static IPortableJukeboxSettings get(ItemStack stack)
	{
		return stack.getCapability(pjbs, null);
	}
}
