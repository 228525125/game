package org.cx.game.command;

import org.cx.game.command.expression.Calculator;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.SyntaxValidatorException;

public class CommandFactory {
	
	public static InteriorCommand getInstance(IPlayer player, String cmd) throws SyntaxValidatorException {
		Calculator helper = new Calculator();
		return helper.parseForCommand(player, cmd);
	}
}
