package checkstyle.extension.tests.system;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.sf.eclipsecs.sample.checks.meaninglessTermsCheck;

public class MeaninglessTermsSystemTest {

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
	public void testLoadingDictionary() {
			
			long startTime = System.currentTimeMillis();
			
			meaninglessTermsCheck check = spy(new meaninglessTermsCheck());
			
			check.readDictionary();
			
			long finishTime = System.currentTimeMillis();

			System.out.println("That took: "+(finishTime-startTime)+ " ms");
			
			assertNotNull(check.getDict());
		
	}

}
