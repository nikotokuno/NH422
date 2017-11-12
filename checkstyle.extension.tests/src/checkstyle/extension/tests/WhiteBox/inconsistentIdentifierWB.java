package checkstyle.extension.tests.WhiteBox;

import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.sf.eclipsecs.sample.checks.inconsistentIdentifiersCheck;

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
	public void inconsistentIdentifierstest() {
		inconsistentIdentifiersCheck IICheck = new inconsistentIdentifiersCheck();
		
		assertEquals(false,  IICheck.checkConsistency("path", "absolute_path"));
		assertEquals(false,  IICheck.checkConsistency("path", "absolutePath"));
		assertEquals(true,  IICheck.checkConsistency("path", "path"));
		assertEquals(true,  IICheck.checkConsistency("absolutePath", "relativePath"));
	}
	public void testCheckMethods()
	{
		inconsistentIdentifiersCheck IICheck = new inconsistentIdentifiersCheck();
		ArrayList<String> names = new ArrayList<String>();
		names.add("Object");
		names.add("setDictionary");
		names.add("createObject");
		names.add("saveObject");
		names.add("getDictionary");
		names.add("Dictionary");
		names.add("create");
		assertEquals(false, IICheck.checkMethods(names));
		assertEquals(true, IICheck.checkMethods(names));
		
	}

}
