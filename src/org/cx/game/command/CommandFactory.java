package org.cx.game.command;

import org.cx.game.command.expression.Calculator;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.SyntaxValidatorException;

public class CommandFactory {
	
	public static Command getInstance(String cmd, CommandBuffer buffer) throws SyntaxValidatorException {
		Calculator helper = new Calculator();
		
		return helper.parseForCommand(cmd, buffer);
	}
	
	public static Command getInstance(String cmd) throws SyntaxValidatorException {
		Calculator helper = new Calculator();
		
		return helper.parseForCommand(cmd);
	}
}
