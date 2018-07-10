package org.cx.game.command;

import org.cx.game.command.expression.Calculator;
import org.cx.game.core.AbstractPlayer;
import org.cx.game.exception.SyntaxValidatorException;

public class CommandFactory {
	
	public static InteriorCommand getInstance(AbstractPlayer player, String cmd) throws SyntaxValidatorException {
		Calculator helper = new Calculator();
		return helper.parseForCommand(player, cmd);
	}
}
