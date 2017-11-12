package checkstyle.extension.tests.integration;

import static org.junit.Assert.*;



import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.mockito.Mockito.*;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import net.sf.eclipsecs.sample.checks.ExtremeContradictionCheck;
import net.sf.eclipsecs.sample.checks.meaninglessTermsCheck;

@RunWith(PowerMockRunner.class)
@PrepareForTest({meaninglessTermsCheck.class,DetailAST.class})
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
	public void testSetDictionary() {
		meaninglessTermsCheck check = PowerMockito.mock(meaninglessTermsCheck.class);
		doReturn(false).when(check).isMeaninglessTerm("create");
		check.readDictionary();
		assertNotNull(check.getDict());
	}
	
	@Test
	public void testIsMeaninglessTerm() {
		meaninglessTermsCheck check = PowerMockito.mock(meaninglessTermsCheck.class);
		DetailAST ast = PowerMockito.mock(DetailAST.class);
		
		check.readDictionary();
		boolean result = check.isMeaninglessTerm(ast.getText());

		assertEquals(result, check.isMeaninglessTerm(ast.getText()));
		assertNotNull(check.getDict());
	}
	
	@Test
	public void testVisitToken() {
		meaninglessTermsCheck check = PowerMockito.mock(meaninglessTermsCheck.class);
		DetailAST ast = PowerMockito.mock(DetailAST.class);
		
		doReturn(ast).when(ast).findFirstToken(TokenTypes.IDENT);
		doReturn("foo").when(ast).getText();
		check.visitToken(ast);
		boolean result = check.isMeaninglessTerm(ast.getText());
		
		verify(check).visitToken(ast);
		assertEquals(result, check.isMeaninglessTerm(ast.getText()));
		assertNotNull(check.getDict());
	}

}
