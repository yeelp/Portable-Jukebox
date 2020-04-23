package yeelp.portablejukebox.util;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class PortableJukeboxSettingsProvider 
{
	@CapabilityInject(IPortableJukeboxSettings.class)
	public static Capability<IPortableJukeboxSettings> pjbs = null;
	
	private IPortableJukeboxSettings instance =  pjbs.getDefaultInstance();
	
	public static IPortableJukeboxSettings get(PortableJukeboxSettings settings)
	{
		return settings.getCapability(pjbs, null);
	}
}
