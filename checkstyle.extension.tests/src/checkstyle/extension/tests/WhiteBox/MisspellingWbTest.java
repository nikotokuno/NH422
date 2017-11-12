package checkstyle.extension.tests.WhiteBox;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.sf.eclipsecs.sample.checks.MisspellingCheck;

public class MisspellingWbTest {

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
		MisspellingCheck mScheck = new MisspellingCheck();
		HashSet<String> tempDict = new HashSet<String>();
		
		// Act
		mScheck.setEnglishDictionary();
		HashSet<String> returnValue = mScheck.getEnglishDictionary();
		
		// Assert
		assertNotNull(returnValue);
	}
	
	@Test
	public void testIsNotMisspelled() {
		String testWordOne = "Check";
		String testWordTwo = "Elementary";
		String testWordThree = "Pencil";
		
		MisspellingCheck mScheck = new MisspellingCheck();
		
		boolean return1 = mScheck.isMisspelled(testWordOne);
		boolean return2 = mScheck.isMisspelled(testWordTwo);
		boolean return3 = mScheck.isMisspelled(testWordThree);
		
		assertFalse(return1);
		assertFalse(return2);
		assertFalse(return3);
	}
	
	@Test
	public void testIsMisspelled() {
		String testWordOne = "Chcek";
		String testWordTwo = "Elmentary";
		String testWordThree = "Pencal";
		
		MisspellingCheck mScheck = new MisspellingCheck();
		
		boolean return1 = mScheck.isMisspelled(testWordOne);
		boolean return2 = mScheck.isMisspelled(testWordTwo);
		boolean return3 = mScheck.isMisspelled(testWordThree);
		
		assertTrue(return1);
		assertTrue(return2);
		assertTrue(return3);
	}

}
