package checkstyle.extension.tests.WhiteBox;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.sf.eclipsecs.sample.checks.ExtremeContradictionCheck;
import net.sf.eclipsecs.sample.checks.MisspellingCheck;

public class ExtremeContradictionWbTest {

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
	public void testDictionary() {
		// Arrange
		ExtremeContradictionCheck eCcheck = new ExtremeContradictionCheck();
		HashSet<String> tempDict = new HashSet<String>();
		
		// Act
		eCcheck.setEnglishDictionary();
		HashSet<String> returnValue = eCcheck.getEnglishDictionary();
		
		// Assert
		assertNotNull(returnValue);
	}
	
	@Test
	public void testIsIgnoreSituation() {
		ExtremeContradictionCheck eCcheck = new ExtremeContradictionCheck();
		String testWordOne = "Tacos";
		String testWordTwo = "Elephant";
		
		boolean returnValOne = eCcheck.isIgnoreSituation(testWordOne);
		boolean returnValTwo = eCcheck.isIgnoreSituation(testWordTwo);
		
		assertTrue(returnValOne);
		assertTrue(returnValTwo);
	}
	
	@Test
	public void testIsNotIgnoreSituation() {
		ExtremeContradictionCheck eCcheck = new ExtremeContradictionCheck();
		String testWordOne = "Abc";
		String testWordTwo = "lol";
		
		boolean returnValOne = eCcheck.isIgnoreSituation(testWordOne);
		boolean returnValTwo = eCcheck.isIgnoreSituation(testWordTwo);
		
		assertFalse(returnValOne);
		assertFalse(returnValTwo);
	}

}
