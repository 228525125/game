package org.cx.game.command.expression;

import org.cx.game.exception.SyntaxValidatorException;

public interface IExpression {

	public Object interpreter()throws SyntaxValidatorException;
}
