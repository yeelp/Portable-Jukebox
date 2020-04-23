package yeelp.portablejukebox.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import yeelp.portablejukebox.PortableJukebox;
import yeelp.portablejukebox.handler.GuiHandler;
import yeelp.portablejukebox.handler.MusicHandler;
import yeelp.portablejukebox.item.PortableJukeboxItem;
import yeelp.portablejukebox.util.PortableJukeboxSettings;

/**
 * PortableJukebox Proxy
 * @author Yeelp
 *
 */
@Mod.EventBusSubscriber
public abstract class CommonProxy 
{
	/**
	 * Proxy preinit
	 * @param evt the preinit event
	 */
	public void preInit(FMLPreInitializationEvent evt)
	{
		PortableJukeboxSettings.register();
	}
	
	/**
	 * Proxy init
	 * @param evt the init event
	 */
	public void init(FMLInitializationEvent evt)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(PortableJukebox.instance, new GuiHandler());
		new MusicHandler().register();
	}
	
	/**
	 * Proxy post init
	 * @param evt the post init event
	 */
	public void postInit(FMLPostInitializationEvent evt)
	{
		
	}
	
	
	@SubscribeEvent
	public static final void registerItems(RegistryEvent.Register<Item> evt)
	{
		evt.getRegistry().register(new PortableJukeboxItem());
	}
}
