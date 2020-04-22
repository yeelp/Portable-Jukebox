package tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import yeelp.portablejukebox.PortableJukebox;

/**
 * A Test runner to verify some correctness before launching PortableJukebox.
 * @author Yeelp
 *
 */
public class TestRunner 
{
	public static void run() throws RuntimeException
	{
		Result result = JUnitCore.runClasses(AllTests.class);
		if(result.wasSuccessful())
		{
			PortableJukebox.info("All tests successful!");
		}
		else
		{
			PortableJukebox.fatal("Preinitialization tests failed! Please open an issue at https://github.com/yeelp/Portable-Jukebox/issues and include the following messages:");
			for(Failure failure : result.getFailures())
			{
				PortableJukebox.fatal(failure.toString());
			}
			PortableJukebox.fatal("To ensure unexpected behaviour doesn't occur during gameplay, Minecraft will NOT launch.");
			throw new RuntimeException("Portable Jukebox failed preinit tests and will not load.");
		}
	}
}
