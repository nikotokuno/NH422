package net.sf.eclipsecs.sample.checks;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import net.didion.jwnl.JWNLException;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.RAMDictionary;
import edu.mit.jwi.data.ILoadPolicy;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.ISynsetID;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.mit.jwi.item.Pointer;


public class hyponymyCheck extends AbstractCheck{

	private String className;
	private String superClassName;
	
	public IDictionary dict;
	
	//private HashSet<String> dictionary;
	private List<String> result;
	@Override
	public int[] getDefaultTokens() {
	    return new int[] {TokenTypes.CLASS_DEF};
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.puppycrawl.tools.checkstyle.api.AbstractCheck#visitToken(com.puppycrawl.tools.checkstyle.api.DetailAST)
	 */
	@Override
	public void visitToken(DetailAST ast) {
		setClassName(ast.findFirstToken(TokenTypes.IDENT).getText());
		
		if(hasExtends(ast)) {
			loadDictionary();
			setResult(getHypernyms(superClassName));
			if(!(superClassName == null))
			{
				if(!(result.contains(className)))
				{
					log(ast.getLineNo(), "No hyponymy/hypernymy in class hierarchies");
				}
			}
		}
		
		
	}
		
	
	/**
	 * Function returns a List of Hypo and Hypernyms of a given string
	 * @param s Word for which you want to get Hypo and Hypersyms
	 * @return List of Hypo and Hypernyms
	 * @throws JWNLException
	 */
	public List<String> getHypernyms(String s)
	{
		List<String> result = new ArrayList<String>();
		IIndexWord iw = null;
		iw=dict.getIndexWord(s,POS.NOUN);
		if(iw!=null){

			IWordID wordID = iw.getWordIDs().get(0);
			IWord word = dict.getWord(wordID);
			ISynset synset = word.getSynset();

			List<ISynsetID> relatedSynsets = synset.getRelatedSynsets(Pointer.HYPERNYM);
			relatedSynsets.addAll(synset.getRelatedSynsets(Pointer.HYPONYM));

			List <IWord> words;
			for (ISynsetID sid : relatedSynsets) {
				words = dict.getSynset(sid ).getWords () ;
				for(Iterator < IWord > i = words.iterator () ; i.hasNext () ;)
				{
					result.add(i.next().getLemma());
				}
			}
		}
		return result;
	}


	
	/*
	 * 
	 */
	private void loadDictionary() {
		try {
			File dictionary = new File("dictionary.txt");
			dict = new RAMDictionary(dictionary, ILoadPolicy.NO_LOAD);
			dict.open();
			
		} catch (IOException e) {
			
			throw new RuntimeException("couldn't open dictionary",e);
		}
		
	}

	/**
	 * 
	 * @param ast
	 * @return
	 */
	private boolean hasExtends(DetailAST ast) {
		if(ast.getType() == TokenTypes.EXTENDS_CLAUSE)
		{
			DetailAST node = ast.findFirstToken(TokenTypes.EXTENDS_CLAUSE);
			setSuperClassName(node.findFirstToken(TokenTypes.IDENT).getText());
			return true;
		}
		return false;
	}
	
	/*
	 * 
	 */
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getSuperClassName() {
		return superClassName;
	}

	public void setSuperClassName(String supperClassName) {
		this.superClassName = supperClassName;
	}

	public List<String> getResult() {
		return result;
	}

	public void setResult(List<String> result) {
		this.result = result;
	}

}
