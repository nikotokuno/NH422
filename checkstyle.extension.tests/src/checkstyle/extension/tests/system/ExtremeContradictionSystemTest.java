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
		
		ExtremeContradictionCheck mCheck = spy(new ExtremeContradictionCheck());
		
		mCheck.setEnglishDictionary();
		
		doReturn(mCheck.getEnglishDictionary()).when(mCheck).readDictionaryWordsFromFile();
		
		long finishTime = System.currentTimeMillis();

		System.out.println("That took: "+(finishTime-startTime)+ " ms");
		
		assertNotNull(mCheck.getEnglishDictionary());
	}
	
	@Test
	public void testPerformanceForIsFalseIgnoreSituation() {
		long startTime = System.currentTimeMillis();
		ExtremeContradictionCheck mCheck = spy(new ExtremeContradictionCheck());
		String wordCheck = "aBz";
		
		doReturn(false).when(mCheck).isJavaKeyword(wordCheck);
		doReturn(false).when(mCheck).isIgnoreSituation(wordCheck);
		
		long finishTime = System.currentTimeMillis();

		System.out.println("That took: "+(finishTime-startTime)+ " ms");
		
		assertFalse(mCheck.isJavaKeyword(wordCheck));
		assertFalse(mCheck.isIgnoreSituation(wordCheck));
				
	}
	

	@Test
	public void testPerformanceForIsTrueIgnoreSituation() {
		long startTime = System.currentTimeMillis();
		ExtremeContradictionCheck mCheck = spy(new ExtremeContradictionCheck());
		String wordCheck = "Apple";
		
		doReturn(false).when(mCheck).isJavaKeyword(wordCheck);
		doReturn(true).when(mCheck).isIgnoreSituation(wordCheck);
		
		long finishTime = System.currentTimeMillis();

		System.out.println("That took: "+(finishTime-startTime)+ " ms");
		
		assertFalse(mCheck.isJavaKeyword(wordCheck));
		assertTrue(mCheck.isIgnoreSituation(wordCheck));
				
	}

}
