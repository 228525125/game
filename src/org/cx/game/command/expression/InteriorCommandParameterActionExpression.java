package org.cx.game.command.expression;

import org.cx.game.exception.SyntaxValidatorException;
import org.cx.game.validator.InteriorCommandParameterExpressionFormatValidator;
import org.cx.game.validator.InteriorCommandParameterExpressionStringTypeValidator;
import org.dom4j.Element;

public class InteriorCommandParameterActionExpression extends
		InteriorCommandParameterExpression {

	public InteriorCommandParameterActionExpression(String cmd,
			Element cmdEl) {
		super(cmd, cmdEl);
		// TODO Auto-generated constructor stub
		addValidator(new InteriorCommandParameterExpressionStringTypeValidator(getParameter(cmd), cmdEl));
	}
	
	@Override
	public Object interpreter() throws SyntaxValidatorException {
		// TODO Auto-generated method stub
		super.interpreter();
		
		return getParameter();
	}

}
