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


	public ArrayList<String> variableNames = new ArrayList<String>();
	public ArrayList<String> methodNames = new ArrayList<String>();

	@Override
	public void visitToken(DetailAST ast) {
		if(ast.getType() == TokenTypes.OBJBLOCK)
		{
		 	DetailAST node = ast.getFirstChild();

		 	while(node != null)
		 	{
		 		if(node.getType() == TokenTypes.VARIABLE_DEF)
		 		{
		 			addVariableNames(node, this.variableNames);
		 		}
		 		else if(node.getType() == TokenTypes.METHOD_DEF)
		 		{
		    		addMethodNames(node, this.methodNames);
		    	}
		    	node = node.getNextSibling();

		    	if(!(checkMethods(this.methodNames) && checkVariables(this.variableNames)) == true)
				{
					log(node.getLineNo(), "inconsistent identifier");
				}
		    }
		}

	}


    public boolean checkMethods(ArrayList<String> names) {

    	int countItems = names.size();
    	boolean check = false;
    	int i = 0;

    	if(countItems > 1)
    	{
    		while (i != names.size() - 1)
    		{
    			check = checkConsistency(names.get(i), names.get(i+1));
    			if(check == true)
    			{
    				break;
    			}
    			i++;
    		}
    	}
    	return check;

	}

	public boolean checkVariables(ArrayList<String> names) {
		int countItems = names.size();
    	boolean check = false;
    	int i = 0;

    	if(countItems > 1)
    	{
    		while (i != names.size() - 1)
    		{
    			check = checkConsistency(names.get(i), names.get(i+1));
    			if(check == true)
    			{
    				break;
    			}
    			i++;
    		}
    	}
    	return check;
	}

	public void addMethodNames(DetailAST node, ArrayList<String> names)
    {
    	DetailAST child = node.getFirstChild();
    	DetailAST children = child.findFirstToken(TokenTypes.SLIST);

    	while(child != null)
    	{
    		if(child.getType() == TokenTypes.SLIST)
    		{
    			while(children != null)
    			{
    				addVariableNames(children, this.variableNames);
    				children = children.getNextSibling();
    			}
    		}
    		else if(child.getType() == TokenTypes.IDENT)
    		{
    			names.add(child.getText());
    		}
    		child = child.getNextSibling();
    	}
	}

	public void addVariableNames(DetailAST node, ArrayList<String> names)
    {
		DetailAST child = node.findFirstToken(TokenTypes.IDENT);
        names.add(child.getText());

    }


    public boolean checkConsistency(String identifierName1, String identifierName2)
    {
    	identifierName1 = identifierName1.toLowerCase();
    	identifierName2 = identifierName2.toLowerCase();
    	if (identifierName2.contains(identifierName1) && !(identifierName1 == identifierName2))
    	{
    		return false;
    	}
    	return true;
    }
}
