package net.sf.eclipsecs.sample.checks;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.Collator;
import java.util.*;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.utils.CheckUtils;
import com.puppycrawl.tools.checkstyle.utils.JavadocUtils;

import antlr.Utils;

/**
 * @author nikot
 *
 * 	Extreme contraction: refers to extremely short terms
 *	used in identifiers due to an excessive word contraction,
 *	abbreviation, or acronym. An example of such identifier is
 *	aSz (a=array, sz=size). 
 *
 *	This rule does not apply to prefixes
 *	introduced due to the naming conventions adopted in the
 *	system (e.g., m is a prefix used in the Hungarian notation to
 *	mark attributes of a class), common programming and domain
 *	terms (e.g., msg, SQL, etc.), and short dictionary words (e.g.,
 *	on, it, etc.).
 *
 *
 */
public class ExtremeContradictionCheck extends AbstractCheck {
	
    /**
     * Warning message key.
     */
    public static final String MESSAGE_KEY = "extremecontradiction.in.identifier";
	
	/**
     * Set of allowed English words.
     */
	private HashSet<String> englishDictionary;
	
	/**
     * Set of allowed abbreviation to ignore in check.
     */
    private Set<String> allowedAbbreviations = new HashSet<>();
    
    /**
     * Variable indicates on the allowed amount of letters in
     * in identifiers to check for. Default is 3. 
     */
    private int allowedAbbreviationLength = 3;
    
    public int getAllowedAbbreviationLength() {
    	return this.allowedAbbreviationLength;
    }
	
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
		
/*		File f = new File(".");
		for(String fileNames : f.list()) System.out.println(fileNames);*/
		
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
	
    /**
     * Allowed abbreviation length in names.
     * @param allowedAbbreviationLength
     *            amount of allowed capital letters in abbreviation.
     */
    public void setAllowedAbbreviationLength(int allowedAbbreviationLength) {
        this.allowedAbbreviationLength = allowedAbbreviationLength;
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
	            
	            if (!isIgnoreSituation(typeName.toLowerCase())) {
	            	log(nameAst.getLineNo(), MESSAGE_KEY);
	            }
	  }
	
	 public final Collator englishCollator = Collator.getInstance(Locale.ENGLISH);
	 public final String keywords[] = {
	                  "abstract",  "assert",       "boolean",    "break",     "byte",      "case",
	                  "catch",     "char",         "class",      "const",     "continue",
	                  "default",   "do",           "double",     "else",      "extends",
	                  "false",     "final",        "finally",    "float",     "for",
	                  "goto",      "if",           "implements", "import",    "instanceof",
	                  "int",       "interface",    "long",       "native",    "new",
	                  "null",      "package",      "private",    "protected", "public",
	                  "return",    "short",        "static",     "strictfp",  "super",
	                  "switch",    "synchronized", "this",       "throw",     "throws",
	                  "transient", "true",         "try",        "void",      "volatile",
	                  "while"
	              };


	      public boolean isJavaKeyword(String keyword) {
	                 return (Arrays.binarySearch(keywords, keyword, englishCollator) >= 0);
	      } 
	 
	 
	    /**
	     * Checks if it is an ignore situation.
	     * @param ast input DetailAST node.
	     * @return true if it is an ignore situation found for given input DetailAST
	     *         node.
	     */
	    public boolean isIgnoreSituation(String ident) {
	    	
	    	setEnglishDictionary();

	        boolean result = false;
	        
	        // if identifier has more characters than the check's length, ignore.
	        if (ident.length() > allowedAbbreviationLength) {
	            result = true;
	        }
	        
	        // Ignore if the identifier is in the English dictionary i.e. it, on
	        else if (englishDictionary.contains(ident)) {
	            result = true;
	        }
	        
	        // Ignore if user has identified the abbreviation as allowed
	        else if (allowedAbbreviations.contains(ident)) {
	            result = true;
	        }
	        
	        // Ignore if the identifier is a Java keyword 
	        else {
	            result = isJavaKeyword(ident);
	        }
	        return result;
	    }
}
