package org.cx.game.command.expression;

import org.cx.game.exception.SyntaxValidatorException;
import org.dom4j.Element;

public interface IExpression {

	public Object interpreter()throws SyntaxValidatorException;
}
