/**
 * 
 */
package checkstyle.extension.tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.sf.eclipsecs.sample.checks.MisspellingCheck;

import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.utils.CommonUtils;

/**
 * @author nikot
 *
 */
public class MisspellingCheckTest {

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
	public void testGetEnglishDictionary() {
		// Arrange
		MisspellingCheck ecCheck = spy(new MisspellingCheck());
		HashSet<String> tempDict = new HashSet<String>();
				
		// Act
		ecCheck.setEnglishDictionary();
		doReturn(tempDict).when(ecCheck).getEnglishDictionary();
				
		try {
			Scanner file = new Scanner(new File("C:\\Users\\nikot\\Documents\\TEMP\\NH422\\net.sf.eclipsecs.sample\\dictionary.txt"));
				
			while (file.hasNext()) {
				tempDict.add(file.next().trim().toLowerCase());	
			}
			} catch (FileNotFoundException e) {
					
				e.printStackTrace();
			}
				
		// Assert
				
		assertNotNull(ecCheck.getEnglishDictionary());
		assertEquals(tempDict, ecCheck.getEnglishDictionary());
	}
	
	@Test
	public void testSetEnglishDictionary() {
		// Arrange
		MisspellingCheck ecCheck = spy(new MisspellingCheck());
		HashSet<String> tempDict = new HashSet<String>();
		
		// Act
		ecCheck.setEnglishDictionary();
		doReturn(tempDict).when(ecCheck).getEnglishDictionary();
		
		// Assert
		
		assertNotNull(ecCheck.getEnglishDictionary());
	}

	@Test
	public void testIsMisspelled() {
		// Arrange
		MisspellingCheck ecCheck = spy(new MisspellingCheck());
		
		// Act
		ecCheck.setEnglishDictionary();
		doReturn(false).when(ecCheck).isMisspelled("apple");
		doReturn(true).when(ecCheck).isMisspelled("appleez");
		
		// Assert
		assertFalse(ecCheck.isMisspelled("apple"));
		assertTrue(ecCheck.isMisspelled("appleez"));
	}
	
}
