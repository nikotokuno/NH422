/**
 * Author: Hasnain Mazhar
 */
package checkstyle.extension.tests;
import static org.junit.Assert.*;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.spy;
import net.sf.eclipsecs.sample.checks.meaninglessTermsCheck;

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
	public void testGetDict() {
		meaninglessTermsCheck check = spy(new meaninglessTermsCheck());
		HashSet<String> temp = new HashSet<String>();
		
		check.readDictionary();
		
		try {
			Scanner file = new Scanner(new File("C:\\Users\\hasnain\\Documents\\Fall2017\\dictionary.txt"));
				
			while (file.hasNext()) {
				temp.add(file.next().trim().toLowerCase());
			}
			file.close();
			} catch (FileNotFoundException e) {
					
				e.printStackTrace();
			}
		
		assertNotNull(check.getDict());
		assertEquals(temp, check.getDict());
	}
	
	@Test
	public void testSetDict() {
		meaninglessTermsCheck check = spy(new meaninglessTermsCheck());
		HashSet<String> temp = new HashSet<String>();
		
		check.setDict(temp);
		assertNotNull(check.getDict());
		
	}
	
	@Test
	public void testReadDictionary() {
		meaninglessTermsCheck check = spy(new meaninglessTermsCheck());
		HashSet<String> temp = new HashSet<String>();
		
		check.readDictionary();
		try {
			Scanner file = new Scanner(new File("C:\\Users\\hasnain\\Documents\\Fall2017\\dictionary.txt"));
				
			while (file.hasNext()) {
				temp.add(file.next().trim().toLowerCase());	
			}
			file.close();
			} catch (FileNotFoundException e) {
					
				e.printStackTrace();
			}
		
		assertEquals(temp, check.getDict());
	}
	
	@Test
	public void testIsMeaningless() {
		meaninglessTermsCheck check = spy(new meaninglessTermsCheck());
		
		check.readDictionary();
		assertEquals(false, check.isMeaninglessTerm("candi"));
		assertEquals(true, check.isMeaninglessTerm("candy"));		
	}

}
