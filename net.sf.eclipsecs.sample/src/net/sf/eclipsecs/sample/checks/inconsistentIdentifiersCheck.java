package net.sf.eclipsecs.sample.checks;

import java.util.ArrayList;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class inconsistentIdentifiersCheck extends AbstractCheck
{
	@Override
	public int[] getDefaultTokens() {
	    return new int[] {TokenTypes.OBJBLOCK, TokenTypes.METHOD_DEF, TokenTypes.VARIABLE_DEF};
	}
	    

	ArrayList<String> variableNames = new ArrayList<String>();
	ArrayList<String> methodNames = new ArrayList<String>();

	@Override
	public void visitToken(DetailAST ast) {
		if(ast.getType() == TokenTypes.OBJBLOCK)
		{
		 	DetailAST node = ast.getFirstChild();
		    		
		 	while(node != null)
		 	{
		 		if(node.getType() == TokenTypes.VARIABLE_DEF)
		 		{
		 			addVariableNames(node);
		 		}
		 		else if(node.getType() == TokenTypes.METHOD_DEF)
		 		{
		    		addMethodNames(node);
		    	}
		    	node = node.getNextSibling();
		    }
		 }
	}
	    
	    
    private boolean checkMethods() {
	    	
    	int countItems = methodNames.size() - 1;
    	boolean check = false;
    	int i = 0, j = 1;
    	
    	if(countItems > 1)
    	{
    		while (i != countItems)
    		{
    			check = checkConsistency(methodNames.get(i), methodNames.get(j));
    			if(j == countItems)
    			{
    				i++;
    				j = i+1;
    			}
    		}
    	}
    	return check;
    	
	}

	private boolean checkVariables() {
		int countItems = variableNames.size() - 1;
    	boolean check = false;
    	int i = 0, j = 1;
    	
    	if(countItems > 1)
    	{
    		while (i != countItems)
    		{
    			check = checkConsistency(variableNames.get(i), variableNames.get(j));
    			if(j == countItems)
    			{
    				i++;
    				j = i+1;
    			}
    		}
    	}
    	return check;
	}

	private void addMethodNames(DetailAST node) 
    {
    	DetailAST child = node.getFirstChild();
    	DetailAST children = child.findFirstToken(TokenTypes.SLIST);
    	
    	while(child != null)
    	{
    		if(child.getType() == TokenTypes.SLIST)
    		{
    			while(children != null)
    			{
    				addVariableNames(children);
    				children = children.getNextSibling();
    			}
    			boolean check = checkVariables();
    	        if (check == false)
    			{
    			 	log(node.getLineNo(), "Inconsistent Identifier");
    			}
    		}
    		else if(child.getType() == TokenTypes.IDENT)
    		{
    			methodNames.add(child.getText());
    		}
    		child = child.getNextSibling();
    	}
    	
    	boolean check = checkMethods();
        if (check == false)
		{
		 	log(node.getLineNo(), "Inconsistent Identifier");
		}
	}

	public void addVariableNames(DetailAST node)
    {
		DetailAST child = node.findFirstToken(TokenTypes.IDENT);
        variableNames.add(child.getText());
        
        boolean check = checkVariables();
        if (check == false)
		{
		 	log(child.getLineNo(), "Inconsistent Identifier");
		}
    }
    
	
    public boolean checkConsistency(String identifierName1, String identifierName2)
    {
    	if (identifierName1.contains(identifierName2) && !(identifierName1 == identifierName2))
    	{
    		return false;
    	}
    	return true;
    }
}
