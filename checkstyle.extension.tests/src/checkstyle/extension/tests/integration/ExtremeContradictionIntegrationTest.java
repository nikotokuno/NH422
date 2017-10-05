/**
 * 
 */
package checkstyle.extension.tests.integration;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.easymock.EasyMock;
import org.easymock.Mock;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnit44Runner;
import org.powermock.api.easymock.PowerMock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.puppycrawl.tools.checkstyle.api.DetailAST;

import antlr.Token;
import antlr.collections.AST;
import antlr.collections.ASTEnumeration;

import static org.mockito.Mockito.*;

import net.sf.eclipsecs.sample.checks.ExtremeContradictionCheck;

/**
 * @author nikot
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ExtremeContradictionCheck.class,DetailAST.class})
public class ExtremeContradictionIntegrationTest {
	
	@Mock
	ExtremeContradictionCheck mCheck;
	
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
		ExtremeContradictionCheck mCheck = spy(new ExtremeContradictionCheck());
		
		mCheck.setEnglishDictionary();
		
		doReturn(mCheck.getEnglishDictionary()).when(mCheck).readDictionaryWordsFromFile();
		
		assertNotNull(mCheck.getEnglishDictionary());
	}
	
	@Test
	public void testIsFalseIgnoreSituation() {
		ExtremeContradictionCheck mCheck = spy(new ExtremeContradictionCheck());
		String wordCheck = "aBz";
		
		doReturn(false).when(mCheck).isJavaKeyword(wordCheck);
		doReturn(false).when(mCheck).isIgnoreSituation(wordCheck);
		
		assertFalse(mCheck.isJavaKeyword(wordCheck));
		assertFalse(mCheck.isIgnoreSituation(wordCheck));
				
	}
	
	@Test
	public void testIsTrueIgnoreSituation() {
		ExtremeContradictionCheck mCheck = spy(new ExtremeContradictionCheck());
		String wordCheck = "Apple";
		
		doReturn(false).when(mCheck).isJavaKeyword(wordCheck);
		doReturn(true).when(mCheck).isIgnoreSituation(wordCheck);
		
		assertFalse(mCheck.isJavaKeyword(wordCheck));
		assertTrue(mCheck.isIgnoreSituation(wordCheck));
				
	}
	
	/* ********************************************************************************************* */
	
	@Test
	public void testExtremeContradictionAllStubbed() {		
		ExtremeContradictionCheck mCheck = spy(new ExtremeContradictionCheck());
		String wordCheck = "Apple";
		
		doReturn(false).when(mCheck).isJavaKeyword(wordCheck);
		doReturn(true).when(mCheck).isIgnoreSituation(wordCheck);
		
		assertFalse(mCheck.isJavaKeyword(wordCheck));
		assertTrue(mCheck.isIgnoreSituation(wordCheck));
	}
	
	@Test
	public void testExtremeContradiction() {
		// Arrange
		ExtremeContradictionCheck mCheck = PowerMockito.mock(ExtremeContradictionCheck.class);
		DetailAST dAST = PowerMockito.mock(DetailAST.class);
		
		// Act
		doReturn("a").when(dAST).getText();
		doReturn(false).when(mCheck).isJavaKeyword("a");
		
		//mCheck.visitToken(dAST);
		
		// Assert
		assertEquals(false, mCheck.isIgnoreSituation(dAST.getText()));
	}
	
	@Test
	public void testExtremeContradiction_RealIsJavaKeyword() {		
		ExtremeContradictionCheck mCheck = spy(new ExtremeContradictionCheck());
		String wordCheck = "Apple";
		
		doReturn(true).when(mCheck).isIgnoreSituation(wordCheck);
		
		assertFalse(mCheck.isJavaKeyword(wordCheck));
		assertTrue(mCheck.isIgnoreSituation(wordCheck));
	}
	
	@Test
	public void testExtremeContradiction_AllReal() {		
		ExtremeContradictionCheck mCheck = new ExtremeContradictionCheck();
		String wordCheck = "Apple";
				
		assertFalse(mCheck.isJavaKeyword(wordCheck));
		assertTrue(mCheck.isIgnoreSituation(wordCheck));
	}

}
