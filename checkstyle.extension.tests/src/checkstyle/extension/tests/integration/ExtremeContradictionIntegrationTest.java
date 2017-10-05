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
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

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
	
	/* 
	 * h = 2
	 * methods:
	 * 	stub: setDictionary(), isIgnoreSituation() 
	 * 	call: isJavaKeyword()
	 */
	@Test
	public void testExtremeContradiction_isJavaKeyword() {		
		ExtremeContradictionCheck mCheck = spy(new ExtremeContradictionCheck());
		String wordCheck = "Apple";
		
		doReturn(true).when(mCheck).isIgnoreSituation(wordCheck);
		mCheck.isIgnoreSituation(wordCheck);
		
		assertFalse(mCheck.isJavaKeyword(wordCheck));
	}
	
	/* 
	 * h = 2
	 * methods:
	 * 	stub: isIgnoreSituation() 
	 * 	call: setDictionary(), isJavaKeyword()
	 */
	@Test
	public void testExtremeContradiction_setDictionary() {		
		ExtremeContradictionCheck mCheck = PowerMockito.mock(ExtremeContradictionCheck.class);
		String wordCheck = "Apple";
		
		doReturn(true).when(mCheck).isIgnoreSituation(wordCheck);

		mCheck.setEnglishDictionary();
		
		assertNotNull(mCheck.getEnglishDictionary());
		assertFalse(mCheck.isJavaKeyword(wordCheck));
	}
	
	/* 
	 * h = 1
	 * methods:
	 * 	stub: visitToken()
	 * 	call: setDictionary(), isJavaKeyword(), isIgnoreSituation
	 */
	@Test
	public void testExtremeContradiction_isIgnoreSituation() {
		// Arrange
		ExtremeContradictionCheck mCheck = PowerMockito.mock(ExtremeContradictionCheck.class);
		DetailAST dAST = PowerMockito.mock(DetailAST.class);
		
		// Act
		doReturn("a").when(dAST).getText();
		mCheck.setEnglishDictionary();
		mCheck.visitToken(dAST);
		
		
		// Assert
		assertEquals(false, mCheck.isIgnoreSituation(dAST.getText()));
		assertFalse(mCheck.isJavaKeyword(dAST.getText()));
		assertNotNull(mCheck.getEnglishDictionary());
	}
	
	/* 
	 * h = 0
	 * methods:
	 * 	stub: 
	 * 	call: visitToken(), setDictionary(), isJavaKeyword(), isIgnoreSituation
	 */
	@Test
	public void testExtremeContradiction_visitToken() {		
		// Arrange
		ExtremeContradictionCheck mCheck = PowerMockito.mock(ExtremeContradictionCheck.class);
		DetailAST dAST = PowerMockito.mock(DetailAST.class);
		
		// Act
		doReturn(dAST).when(dAST).findFirstToken(TokenTypes.IDENT);
		doReturn("abcdefg").when(dAST).getText();
		mCheck.visitToken(dAST);

		
		// Assert
		verify(mCheck).visitToken(dAST);
		assertEquals(false, mCheck.isIgnoreSituation(dAST.getText()));
		assertFalse(mCheck.isJavaKeyword(dAST.getText()));
		assertNotNull(mCheck.getEnglishDictionary());
	}

}
