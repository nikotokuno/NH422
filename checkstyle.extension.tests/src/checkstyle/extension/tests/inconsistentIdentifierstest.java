package checkstyle.extension.tests;

import static org.junit.Assert.*;



import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import org.mockito.Mockito.*;
import static org.mockito.Mockito.spy;

import net.sf.eclipsecs.sample.checks.inconsistentIdentifiersCheck;


public class inconsistentIdentifierstest {

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
	public void testCheckConsistency() {
		inconsistentIdentifiersCheck IICheck = spy(new inconsistentIdentifiersCheck());
		assertEquals(false,  IICheck.checkConsistency("path", "absolute_path"));
		assertEquals(false,  IICheck.checkConsistency("path", "absolutePath"));
		assertEquals(true,  IICheck.checkConsistency("path", "path"));
		assertEquals(true,  IICheck.checkConsistency("absolutePath", "relativePath"));
	}
	
	@Test
	public void testCheckMethods()
	{
		inconsistentIdentifiersCheck IICheck = spy(new inconsistentIdentifiersCheck());
		ArrayList<String> names = new ArrayList<String>();
		names.add("Object");
		names.add("setDictionary");
		names.add("createObject");
		assertEquals(false, IICheck.checkMethods(names));
		
	}
	
	@Test
	public void testCheckVariables()
	{
		inconsistentIdentifiersCheck IICheck = spy(new inconsistentIdentifiersCheck());
		ArrayList<String> names = new ArrayList<String>();
		names.add("path");
		names.add("relative_path");
		assertEquals(false, IICheck.checkVariables(names));
	}

	
}
