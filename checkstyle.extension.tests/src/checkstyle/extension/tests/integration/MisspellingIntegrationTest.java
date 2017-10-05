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

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import net.sf.eclipsecs.sample.checks.ExtremeContradictionCheck;
import net.sf.eclipsecs.sample.checks.MisspellingCheck;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author nikot
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({MisspellingCheck.class,DetailAST.class})
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
	
	/* 
	 * h = 0
	 * methods:
	 * 	stub: visitToken() , isMisspelled()
	 * 	call: setEnglishDictionary()
	 */
	@Test
	public void testMisspelling_setEnglishDictionary() {		
		MisspellingCheck mCheck = PowerMockito.mock(MisspellingCheck.class);
		String testWord = "Apple";
	
		doReturn(false).when(mCheck).isMisspelled(testWord);
		
		mCheck.setEnglishDictionary();
		
		assertNotNull(mCheck.getEnglishDictionary());
	}
	
	/* 
	 * h = 0
	 * methods:
	 * 	stub: visitToken()
	 * 	call: setEnglishDictionary(), isMisspelled()
	 */
	@Test
	public void testMisspelling_isMisaspelled() throws Exception {
		// Arrange
				MisspellingCheck mCheck = PowerMockito.mock(MisspellingCheck.class);
				DetailAST dAST = PowerMockito.mock(DetailAST.class);
				
				// Act
				doReturn("Tacoes").when(dAST).getText();
				mCheck.setEnglishDictionary();
				boolean result = mCheck.isMisspelled(dAST.getText());
				
				// Assert
				assertEquals(result, mCheck.isMisspelled(dAST.getText()));
				assertNotNull(mCheck.getEnglishDictionary());
	}
	
	
	/* 
	 * h = 0
	 * methods:
	 * 	stub: 
	 * 	call: visitToken(), setEnglishDictionary(), isMisspelled()
	 */
	@Test
	public void testMisspelling_visitToken() throws Exception {
		// Arrange
		MisspellingCheck mCheck = PowerMockito.mock(MisspellingCheck.class);
		DetailAST dAST = PowerMockito.mock(DetailAST.class);
		
		// Act
		doReturn(dAST).when(dAST).findFirstToken(TokenTypes.IDENT);
		doReturn("Donuots").when(dAST).getText();
		mCheck.visitToken(dAST);
		boolean result = mCheck.isMisspelled(dAST.getText());
		
		// Assert
		verify(mCheck).visitToken(dAST);
		assertEquals(result, mCheck.isMisspelled(dAST.getText()));
		assertNotNull(mCheck.getEnglishDictionary());
	}
}
