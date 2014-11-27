package org.cx.game.command;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.core.Context;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.ValidatorException;
import org.cx.game.observer.NotifyInfo;

public class StartCommand extends InteriorCommand {

	public StartCommand(IPlayer player) {
		// TODO Auto-generated constructor stub
		super(player);
	}
	
	@Override
	public void execute() throws ValidatorException {
		// TODO Auto-generated method stub
		context.start();
	}
}
