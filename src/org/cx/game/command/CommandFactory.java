package org.cx.game.command;

import java.util.ArrayList;
import java.util.List;

import org.cx.game.command.expression.Calculator;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.SyntaxValidatorException;

public class CommandFactory {
	
	public static InteriorCommand createCommand(IPlayer player, String cmd) throws SyntaxValidatorException{
		Calculator helper = new Calculator();
		return helper.parseForCommand(player, cmd);
	}
	
	/**
	 * 存在语句顺序问题，不推荐使用
	 * @param player
	 * @param cmd
	 * @return
	 * @throws SyntaxValidatorException
	 */
	public static List<InteriorCommand> createCommands(IPlayer player, String cmd) throws SyntaxValidatorException{
		Calculator helper = new Calculator();
		List<InteriorCommand> list = new ArrayList<InteriorCommand>();
		list = helper.parseForList(player, cmd);
		return list;
	}
	
	public static OutsideCommand createCommand(String cmd, IExternalCommand external) throws SyntaxValidatorException{
		Calculator helper = new Calculator();
		return helper.parseForCommand(cmd, external);
	}
	
	/**
	 * 存在语句顺序问题，不推荐使用
	 * @param cmd
	 * @param external
	 * @return
	 * @throws SyntaxValidatorException
	 */
	public static List<OutsideCommand> createCommands(String cmd, IExternalCommand external) throws SyntaxValidatorException{
		Calculator helper = new Calculator();
		List<OutsideCommand> list = new ArrayList<OutsideCommand>();
		list = helper.parseForList(cmd, external);
		return list;
	}
}
