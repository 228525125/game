package org.cx.game.command.expression;

import java.util.Iterator;

import org.cx.game.command.Command;
import org.cx.game.exception.SyntaxValidatorException;
import org.cx.game.tools.XmlUtil;
import org.cx.game.validator.ParameterExpressionRequestValidator;
import org.dom4j.Element;

/**
 * 行动表达式，例如：select、attack、show等
 * @author chenxian
 *
 */
public abstract class CommandExpression extends Expression {
	
	private String cmd = null;
	private Element root = null;	
	private Boolean paramRequest = false;
	private Element commandElement = null;
	
	public CommandExpression(String cmd, Element root) {
		// TODO Auto-generated constructor stub
		this.cmd = cmd;
		this.root = root;
		addValidator(new ParameterExpressionRequestValidator(cmd, root));    //隐含验证CommandExpressionNameValidator
	}

	public String getCmd() {
		return cmd;
	}

	public Element getRoot() {
		return root;
	}

	public Boolean getParamRequest() {
		return paramRequest;
	}

	public void setParamRequest(Boolean paramRequest) {
		this.paramRequest = paramRequest;
	}
	
	public Element getCommandElement() {
		return commandElement;
	}
	
	@Override
	public Object interpreter() throws SyntaxValidatorException {
		// TODO Auto-generated method stub
		
		String action = getAction(getCmd());
		
		Element commands = getRoot().element(XmlUtil.Command_Commands);
		for(Iterator it = commands.elementIterator(XmlUtil.Command_Command);it.hasNext();){
			Element command = (Element) it.next();
			if(action.equals(command.attribute(XmlUtil.Command_Command_Name).getText())){
				this.commandElement = command;
			}
		}
		
		return super.interpreter();
	}
}
