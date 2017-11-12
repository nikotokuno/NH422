package checkstyle.extension.tests.WhiteBox;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import net.sf.eclipsecs.sample.checks.inconsistentIdentifiersCheck;

//@RunWith(PowerMockRunner.class)
//@PrepareForTest({inconsistentIdentifiersCheck.class,DetailAST.class})
public class inconsistentIdentifierWB {

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
	public void checkConsistencytest() {
		inconsistentIdentifiersCheck IICheck = new inconsistentIdentifiersCheck();
		
		assertEquals(false,  IICheck.checkConsistency("path", "absolute_path"));
		assertEquals(false,  IICheck.checkConsistency("path", "absolutePath"));
		assertEquals(true,  IICheck.checkConsistency("path", "path"));
		assertEquals(true,  IICheck.checkConsistency("absolutePath", "relativePath"));
	}

	
	@Test
	public void testCheckMethod() {
		inconsistentIdentifiersCheck check = new inconsistentIdentifiersCheck();
		ArrayList<String> names = new ArrayList<String>();
		names.add("create");
		names.add("createObject");
		//it also checks the checkConsistency method called in checkMethod()
		assertEquals(false, check.checkMethods(names));		
		names.clear();
		names.add("create");
		names.add("delete");
		//it also checks the checkConsistency method called in checkMethod()
		assertEquals(true, check.checkMethods(names));
	}
	
	@Test
	public void testCheckVariables() {
		inconsistentIdentifiersCheck check = new inconsistentIdentifiersCheck();
		ArrayList<String> names = new ArrayList<String>();
		names.add("path");
		names.add("absolutePath");
		//it also checks the checkConsistency method called in checkMethod()
		assertEquals(false, check.checkVariables(names));
		names.clear();
		names.add("relativePath");
		names.add("absolutePath");
		//it also checks the checkConsistency method called in checkMethod()
		assertEquals(true, check.checkVariables(names));	
	}
	/*
	@Test
	public void testVisitToken() {		
		inconsistentIdentifiersCheck check = PowerMockito.mock(inconsistentIdentifiersCheck.class);
		DetailAST ast = PowerMockito.mock(DetailAST.class);
		DetailAST ast2 = PowerMockito.mock(DetailAST.class);
		
		doReturn(ast).when(ast).findFirstToken(TokenTypes.VARIABLE_DEF);
		doReturn(ast).when(ast2).findFirstToken(TokenTypes.METHOD_DEF);
		check.addVariableNames(ast, check.variableNames);
		check.addMethodNames(ast2, check.methodNames);

		verify(check).visitToken(ast);
		verify(check).visitToken(ast2);
		assertNotNull(check.methodNames);
		assertNotNull(check.variableNames);
	}
*/
}
