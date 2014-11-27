package org.cx.game.command.expression;

import java.util.Iterator;

import org.cx.game.exception.SyntaxValidatorException;
import org.dom4j.Element;

public abstract class ParameterExpression extends Expression {

	private String cmd = null;
	private String parameter = null;
	private Element cmdEl = null;	
	
	public ParameterExpression(String cmd, Element cmdEl) {
		// TODO Auto-generated constructor stub
		this.cmd = cmd;
		this.parameter = cmd.substring(cmd.indexOf(Calculator.SPACE)+1);    //这里不用验证，因为前面已经验证过了
		this.cmdEl = cmdEl;
	}

	public String getParameter() {
		return parameter;
	}
}
