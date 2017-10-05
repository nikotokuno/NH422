package checkstyle.extension.tests.integration;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.sf.eclipsecs.sample.checks.meaninglessTermsCheck;


public class meaninglessTermsTest {

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
	public void testReadDictionary() {
		meaninglessTermsCheck check = new meaninglessTermsCheck();
		check.readDictionary();
		assertNotNull(check.getDict());
	}
	

}
