package org.cx.game.command.expression;

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
}
