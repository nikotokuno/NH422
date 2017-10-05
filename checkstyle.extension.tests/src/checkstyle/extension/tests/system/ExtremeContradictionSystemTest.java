package checkstyle.extension.tests.system;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

/* System Test:
 * Functional - 
 * Performance -
 * Stress -
 * Config -
 * Security -
 * Realiability - 
 * Usability - 
 * 
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.sf.eclipsecs.sample.checks.ExtremeContradictionCheck;


/* Functional Reuirements:
 * 
 * 
 * 
 */

public class ExtremeContradictionSystemTest {
	
    private static final long MEGABYTE = 1024L * 1024L;

    public static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testPerformanceForEnglishDictionary() throws Exception {
		long startTime = System.currentTimeMillis();
		
		ExtremeContradictionCheck mCheck = new ExtremeContradictionCheck();
		
		mCheck.setEnglishDictionary();
		
		long finishTime = System.currentTimeMillis();

		System.out.println("English Dictionary: That took: "+(finishTime-startTime)+ " ms");
		
        // Get the Java runtime
        Runtime runtime = Runtime.getRuntime();
        // Run the garbage collector
        runtime.gc();
        // Calculate the used memory
        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("English Dictionary: Used memory is bytes: " + memory);
        System.out.println("English Dictionary: Used memory is megabytes: "
                + bytesToMegabytes(memory));
		
		assertNotNull(mCheck.getEnglishDictionary());
	}
	
	@Test
	public void testPerformanceForIsFalseIgnoreSituation() {
		long startTime = System.currentTimeMillis();
		ExtremeContradictionCheck mCheck = new ExtremeContradictionCheck();
		String wordCheck = "aBz";
		
		long finishTime = System.currentTimeMillis();

		System.out.println("Ignore Situation (false): That took: "+(finishTime-startTime)+ " ms");
		
		 // Get the Java runtime
        Runtime runtime = Runtime.getRuntime();
        // Run the garbage collector
        runtime.gc();
        // Calculate the used memory
        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Ignore Situation (false): Used memory is bytes: " + memory);
        System.out.println("Ignore Situation (false): Used memory is megabytes: "
                + bytesToMegabytes(memory));
		
		assertFalse(mCheck.isJavaKeyword(wordCheck));
		assertFalse(mCheck.isIgnoreSituation(wordCheck));	
	}
	

	@Test
	public void testPerformanceForIsTrueIgnoreSituation() {
		long startTime = System.currentTimeMillis();
		ExtremeContradictionCheck mCheck = new ExtremeContradictionCheck();
		String wordCheck = "Apple";
		
		long finishTime = System.currentTimeMillis();

		System.out.println("Ignore Situation (true): That took: "+(finishTime-startTime)+ " ms");
		
		 // Get the Java runtime
        Runtime runtime = Runtime.getRuntime();
        // Run the garbage collector
        runtime.gc();
        // Calculate the used memory
        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Ignore Situation (true): Used memory is bytes: " + memory);
        System.out.println("Ignore Situation (true): Used memory is megabytes: "
                + bytesToMegabytes(memory));
		
		assertFalse(mCheck.isJavaKeyword(wordCheck));
		assertTrue(mCheck.isIgnoreSituation(wordCheck));
				
	}

}
