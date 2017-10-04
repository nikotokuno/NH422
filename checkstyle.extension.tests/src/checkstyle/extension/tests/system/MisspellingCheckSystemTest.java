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
		
		MisspellingCheck mCheck = spy(new MisspellingCheck());
		
		mCheck.setEnglishDictionary();
		
		doReturn(mCheck.getEnglishDictionary()).when(mCheck).readDictionaryWordsFromFile();
		
		long finishTime = System.currentTimeMillis();

		System.out.println("That took: "+(finishTime-startTime)+ " ms");
		
		assertNotNull(mCheck.getEnglishDictionary());
	}

}
