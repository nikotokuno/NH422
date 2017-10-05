/**
 * 
 */
package checkstyle.extension.tests.system;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.sf.eclipsecs.sample.checks.ExtremeContradictionCheck;
import net.sf.eclipsecs.sample.checks.MisspellingCheck;

/**
 * @author nikot
 *
 */
public class MisspellingCheckSystemTest {
	
	 private static final long MEGABYTE = 1024L * 1024L;

	    public static long bytesToMegabytes(long bytes) {
	        return bytes / MEGABYTE;
	    }

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPerformanceForEnglishDictionary() throws Exception {
		
		long startTime = System.currentTimeMillis();
		
		MisspellingCheck mCheck = new MisspellingCheck();
		
		mCheck.setEnglishDictionary();
		
		long finishTime = System.currentTimeMillis();

		System.out.println("That took: "+(finishTime-startTime)+ " ms");
		
		 // Get the Java runtime
        Runtime runtime = Runtime.getRuntime();
        // Run the garbage collector
        runtime.gc();
        // Calculate the used memory
        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Ignore Situation (false): Used memory is bytes: " + memory);
        System.out.println("Ignore Situation (false): Used memory is megabytes: "
                + bytesToMegabytes(memory));
		
		assertNotNull(mCheck.getEnglishDictionary());
	}

}
