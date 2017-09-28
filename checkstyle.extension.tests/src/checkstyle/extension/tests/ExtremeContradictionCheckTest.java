package checkstyle.extension.tests;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.mockito.Matchers;

import net.sf.eclipsecs.sample.checks.ExtremeContradictionCheck;

import static org.mockito.Mockito.*;

public class ExtremeContradictionCheckTest {

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
	public void testGetDefaultTokens() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEnglishDictionary() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetEnglishDictionary() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetAllowedAbbreviationLength() {
		// Arrange
		ExtremeContradictionCheck ecCheck = spy(new ExtremeContradictionCheck());
		
		// Act
		doReturn(null).when(ecCheck).setAllowedAbbreviationLength(4);
		
		// Assert
		fail("Not yet implemented");
	}

	@Test
	public void testVisitTokenDetailAST() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsJavaKeyword() {
		// Arrange
		ExtremeContradictionCheck ecCheck = spy(new ExtremeContradictionCheck());
		
		doReturn(true).when(ecCheck).isJavaKeyword("abstract");
		doReturn(true).when(ecCheck).isJavaKeyword("do");
		doReturn(false).when(ecCheck).isJavaKeyword("chocolate");
						
		// Assert
		assertTrue(ecCheck.isJavaKeyword("abstract"));
		assertTrue(ecCheck.isJavaKeyword("do"));
		assertFalse(ecCheck.isJavaKeyword("chocolate"));
	}

}
