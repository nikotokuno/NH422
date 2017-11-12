package checkstyle.extension.tests.WhiteBox;

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
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import static org.mockito.Mockito.*;

import net.sf.eclipsecs.sample.checks.inconsistentIdentifiersCheck;
import net.sf.eclipsecs.sample.checks.meaninglessTermsCheck;

//@RunWith(PowerMockRunner.class)
//@PrepareForTest({inconsistentIdentifiersCheck.class,DetailAST.class})
public class MeaninglessTermWB {

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
		assertEquals(false, check.isMeaninglessTerm("stringC"));
		assertEquals(false, check.isMeaninglessTerm("s123"));
		assertEquals(false, check.isMeaninglessTerm("h5$f"));
	}
	/*
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
	*/
}
