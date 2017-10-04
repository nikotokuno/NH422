package checkstyle.extension.tests;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.mockito.Matchers;

import net.sf.eclipsecs.sample.checks.ExtremeContradictionCheck;
import net.sf.eclipsecs.sample.checks.MisspellingCheck;

import static org.mockito.Mockito.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class ExtremeContradictionCheckTest {

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
	public void testGetEnglishDictionary() {
		// Arrange
		ExtremeContradictionCheck ecCheck = spy(new ExtremeContradictionCheck());
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
		ExtremeContradictionCheck ecCheck = spy(new ExtremeContradictionCheck());
		HashSet<String> tempDict = new HashSet<String>();
		
		// Act
		ecCheck.setEnglishDictionary();
		doReturn(tempDict).when(ecCheck).getEnglishDictionary();
		
		// Assert
		
		assertNotNull(ecCheck.getEnglishDictionary());
	}
	
	@Test
	public void testReadDictionaryWordsFromFile() {
		// Arrange
				MisspellingCheck ecCheck = spy(new MisspellingCheck());
				HashSet<String> tempDict = new HashSet<String>();
						
				// Act
				ecCheck.setEnglishDictionary();
				doReturn(tempDict).when(ecCheck).readDictionaryWordsFromFile();
						
				try {
					Scanner file = new Scanner(new File("C:\\Users\\nikot\\Documents\\TEMP\\NH422\\net.sf.eclipsecs.sample\\dictionary.txt"));
						
					while (file.hasNext()) {
						tempDict.add(file.next().trim().toLowerCase());	
					}
					} catch (FileNotFoundException e) {
							
						e.printStackTrace();
					}
						
				// Assert
						
				assertNotNull(ecCheck.readDictionaryWordsFromFile());
				assertEquals(tempDict, ecCheck.readDictionaryWordsFromFile());	
	}

	@Test
	public void testSetAllowedAbbreviationLength() {
		// Arrange
		ExtremeContradictionCheck ecCheck = spy(new ExtremeContradictionCheck());
		
		// Act
		ecCheck.setAllowedAbbreviationLength(4);
		
		// Assert
		assertEquals(4, ecCheck.getAllowedAbbreviationLength());
	}


	@Test
	public void testIsJavaKeyword() {
		// Arrange
		ExtremeContradictionCheck ecCheck = spy(new ExtremeContradictionCheck());
		
		doReturn(true).when(ecCheck).isJavaKeyword("abstract");
		doReturn(true).when(ecCheck).isJavaKeyword("do");
		doReturn(false).when(ecCheck).isJavaKeyword("chocolate");
						
		// Assert
		assertTrue(ecCheck.isJavaKeyword("abstract"));
		assertTrue(ecCheck.isJavaKeyword("do"));
		assertFalse(ecCheck.isJavaKeyword("chocolate"));
	}

}
