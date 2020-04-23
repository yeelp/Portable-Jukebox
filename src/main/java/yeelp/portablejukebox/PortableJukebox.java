package yeelp.portablejukebox;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import tests.AllTests;
import tests.TestRunner;
import yeelp.portablejukebox.proxy.CommonProxy;

import org.apache.logging.log4j.Logger;

@Mod(modid = ModConsts.MODID, name = ModConsts.MODNAME, version = ModConsts.VERSION)
public class PortableJukebox
{
    private static final boolean DEBUG_ON = true;
    private static Logger logger;
    
    @Mod.Instance
	public static PortableJukebox instance;
    
    @SidedProxy(clientSide = ModConsts.CLIENT_PROXY, serverSide = ModConsts.SERVER_PROXY)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        if(DEBUG_ON)
    	{
    		info("PortableJukebox has entered debug mode! Enable debug messages in the console!");
    	}
        //TestRunner.run();
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	proxy.init(event);
    }
    
    @EventHandler 
    public void postInit(FMLPostInitializationEvent event)
    {
    	proxy.postInit(event);
    }
    
    /**
     * Output a message at the debug level. If debug isn't enabled, no message is outputted.
     * @param msg output message
     */
    public static void debug(String msg)
    {
    	if(DEBUG_ON)
    	{
    		logger.info("[PORTABLE JUKEBOX]" + msg);
    	}
    }
    
    /**
     * Output a message at the info level
     * @param msg output message
     */
    public static void info(String msg) 
	{
		logger.info("[PORTABLE JUKEBOX] " + msg);
	}
    
    /**
     * output a message at the fatal level
     * @param msg output message
     */
    public static void fatal(String msg)
    {
    	logger.fatal("[PORTABLE JUKEBOX] " + msg);
    }

    /**
     * output a message at the warning level
     * @param msg output message
     */
	public static void warn(String msg) 
	{
		logger.warn("[PORTABLE JUKEBOX] " + msg);
	}
}
