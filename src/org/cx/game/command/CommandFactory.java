package org.cx.game.command;

import java.util.ArrayList;
import java.util.List;

import org.cx.game.command.expression.Calculator;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.SyntaxValidatorException;

public class CommandFactory {
	
	public static List<InteriorCommand> createCommands(IPlayer player, String cmd) throws SyntaxValidatorException{
		Calculator helper = new Calculator();
		List<InteriorCommand> list = new ArrayList<InteriorCommand>();
		list = helper.parse(player, cmd);
		return list;
	}
	
	public static List<OutsideCommand> createCommands(String cmd, IExternalCommand external) throws SyntaxValidatorException{
		Calculator helper = new Calculator();
		List<OutsideCommand> list = new ArrayList<OutsideCommand>();
		list = helper.parse(cmd, external);
		return list;
	}
}
