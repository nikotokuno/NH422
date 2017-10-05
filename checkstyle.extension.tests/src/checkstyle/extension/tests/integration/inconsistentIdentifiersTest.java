package checkstyle.extension.tests.integration;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.sf.eclipsecs.sample.checks.inconsistentIdentifiersCheck;

public class inconsistentIdentifiersTest {

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

}
