package yeelp.portablejukebox.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import yeelp.portablejukebox.item.PortableJukeboxItem;

public class ClientProxy extends CommonProxy
{
	@Override
	public void preInit(FMLPreInitializationEvent evt)
	{
		super.preInit(evt);
		PortableJukeboxItem.registerRender();
	}
	
	@Override
	public void init(FMLInitializationEvent evt)
	{
		super.init(evt);
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent evt)
	{
		super.postInit(evt);
	}
}
