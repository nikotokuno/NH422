/**
 * 
 */
package net.sf.eclipsecs.sample.checks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

/**
 * @author nikot
 * 
 *  Misspelling: refers to misspelled words in an identifier
 *  
 */
public class MisspellingCheck extends AbstractCheck {
	
	/**
     * Warning message key.
     */
    public static final String MESSAGE_KEY = "misspelling.in.word";
	
	/**
     * Set of allowed English words.
     */
	private HashSet<String> englishDictionary;
	
	/**
	 * @return the englishDictionary
	 */
	public HashSet<String> getEnglishDictionary() {
		return englishDictionary;
	}
	
	/**
	 * @param englishDictionary the englishDictionary to set
	 */
	public void setEnglishDictionary() {
		this.englishDictionary = readDictionaryWordsFromFile();
	}
	
	public HashSet<String> readDictionaryWordsFromFile() {
		HashSet<String> dictionary = new HashSet<String>();
		
		try {
			Scanner file = new Scanner(new File("C:\\Users\\nikot\\Documents\\TEMP\\NH422\\net.sf.eclipsecs.sample\\dictionary.txt"));
			
			while (file.hasNext()) {
				dictionary.add(file.next().trim().toLowerCase());	
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}

		
		return dictionary;
	}

	@Override
	public int[] getDefaultTokens() {
		 return new int[] {
		            TokenTypes.CLASS_DEF,
		            TokenTypes.INTERFACE_DEF,
		            TokenTypes.ENUM_DEF,
		            TokenTypes.ANNOTATION_DEF,
		            TokenTypes.ANNOTATION_FIELD_DEF,
		            TokenTypes.PARAMETER_DEF,
		            TokenTypes.VARIABLE_DEF,
		            TokenTypes.METHOD_DEF,
		        };
	}
	
	 @Override
	 public void visitToken(DetailAST ast) {
		 		
		 
	            final DetailAST nameAst = ast.findFirstToken(TokenTypes.IDENT);
	            final String typeName = nameAst.getText();
	            
	            // check misspelling of identifier
	            if(isMisspelled(typeName) == true) {
	            	log(nameAst.getLineNo(), MESSAGE_KEY);
	            }
	     }
	 
	 public boolean isMisspelled(String word) {
		 this.setEnglishDictionary();
		 
		 boolean isMisspelled = true;

		 
		 if(this.englishDictionary.contains(word.toLowerCase())) {
			 isMisspelled = false;
		 }
		 
		 return isMisspelled;
	 }
}

