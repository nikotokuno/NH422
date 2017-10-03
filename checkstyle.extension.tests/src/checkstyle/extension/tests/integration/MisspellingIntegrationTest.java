/**
 * 
 */
package checkstyle.extension.tests.integration;

import org.easymock.EasyMock;
import org.easymock.Mock;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import net.sf.eclipsecs.sample.checks.ExtremeContradictionCheck;
import net.sf.eclipsecs.sample.checks.MisspellingCheck;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * @author nikot
 *
 */
public class MisspellingIntegrationTest {

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
	public void testEnglishDictionary() throws Exception {
		MisspellingCheck mCheck = spy(new MisspellingCheck());
		
		mCheck.setEnglishDictionary();
		
		doReturn(mCheck.getEnglishDictionary()).when(mCheck).readDictionaryWordsFromFile();
		
		assertNotNull(mCheck.getEnglishDictionary());
	}
}
