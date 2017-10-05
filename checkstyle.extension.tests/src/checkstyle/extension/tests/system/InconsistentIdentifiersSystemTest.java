package checkstyle.extension.tests.system;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.spy;
import net.sf.eclipsecs.sample.checks.inconsistentIdentifiersCheck;

public class InconsistentIdentifiersSystemTest {

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
		long startTime = System.currentTimeMillis();
		inconsistentIdentifiersCheck check = spy(new inconsistentIdentifiersCheck());
		ArrayList<String> names = new ArrayList<String>();
		names.add("create");
		names.add("createObject");
		//it also checks the checkConsistency method called in checkMethod()
		assertEquals(false, check.checkMethods(names));		
		names.clear();
		names.add("create");
		names.add("delete");
		long finishTime = System.currentTimeMillis();
		System.out.println("That took: "+(finishTime-startTime)+ " ms");
		//it also checks the checkConsistency method called in checkMethod()
		assertEquals(true, check.checkMethods(names));
	}
	
	@Test
	public void testCheckVariables() {
		long startTime = System.currentTimeMillis();
		inconsistentIdentifiersCheck check = spy(new inconsistentIdentifiersCheck());
		ArrayList<String> names = new ArrayList<String>();
		names.add("path");
		names.add("absolutePath");
		assertEquals(false, check.checkVariables(names));
		names.clear();
		names.add("relativePath");
		names.add("absolutePath");
		long finishTime = System.currentTimeMillis();
		System.out.println("That took: "+(finishTime-startTime)+ " ms");
		assertEquals(true, check.checkVariables(names));	
	}

}
