/**
 * Author: Hasnain Mazhar
 */
package net.sf.eclipsecs.sample.checks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class meaninglessTermsCheck extends AbstractCheck{
	
	private HashSet<String> dict;
	
	@Override
	public int[] getDefaultTokens() {
	    return new int[] {TokenTypes.CLASS_DEF, TokenTypes.METHOD_DEF, 
	    				  TokenTypes.INTERFACE_DEF, TokenTypes.ENUM_DEF};}
	
	@Override
	 public void visitToken(DetailAST ast) {
		readDictionary();
		DetailAST node = ast.findFirstToken(TokenTypes.IDENT);
	    String name = node.getText();
	            
	            // check misspelling of identifier
	            if(isMeaninglessTerm(name) == true) {
	            	log(node.getLineNo(), "Meaningless Term");
	            }
	     }
	
	/*
	 * return true if the term is in dictionary
	 */
	public boolean isMeaninglessTerm(String name) {
		name = name.toLowerCase();
		if(!(this.dict.contains(name)))
		{
			return false;
		}
		return true;
	}
	
	/*
	 * reads the dictionary from a file
	 */
	public void readDictionary() {
		HashSet<String> dict = new HashSet<String>();
		
		try 
		{
			Scanner file = new Scanner(new File("C:\\Users\\hasnain\\Documents\\Fall2017\\dictionary.txt"));
			
			while (file.hasNext()) {
				dict.add(file.next().trim().toLowerCase());	
			}
			setDict(dict);
			file.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}

	}
	
	/**
	 * 
	 * @return dictionary
	 */
	public HashSet<String> getDict() {
		return dict;
	}
	
	
	/**
	 * set dictionary
	 * @param dict
	 */
	public void setDict(HashSet<String> dict) {
		this.dict = dict;
	}
	
	

}
