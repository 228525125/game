package org.cx.game.command.expression;

import org.cx.game.exception.SyntaxValidatorException;
import org.dom4j.Element;

public class OutsideCommandParameterExpression extends ParameterExpression {

	public OutsideCommandParameterExpression(String cmd, Element cmdEl) {
		super(cmd, cmdEl);
		// TODO Auto-generated constructor stub		
	}
	
	@Override
	public Object interpreter() throws SyntaxValidatorException {
		// TODO Auto-generated method stub
		Object ret = super.interpreter();
		
		ret = getParameter();
		
		return ret;
	}

}
