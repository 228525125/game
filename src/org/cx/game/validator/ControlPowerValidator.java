package org.cx.game.validator;

import org.cx.game.command.Command;
import org.cx.game.command.ReloadCommand;
import org.cx.game.command.SelectCommand;
import org.cx.game.command.ShowCommand;
import org.cx.game.core.IContext;
import org.cx.game.core.IPlayer;
import org.cx.game.tools.I18n;

/**
 * 执行命令前，验证是否具有控制权
 * @author chenxian
 *
 */
public class ControlPowerValidator extends Validator {

	private IPlayer player = null;
	private Command command = null;
	
	public ControlPowerValidator(IPlayer player, Command command) {
		// TODO Auto-generated constructor stub
		this.player = player;
		this.command = command;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		IContext context = player.getContext();
		if(command instanceof SelectCommand || command instanceof ShowCommand || command instanceof ReloadCommand)		
			return true;
		else if(player.equals(context.getControlPlayer()))
			return true;
		else{
			addMessage(I18n.getMessage(this));
			return false;
		}
	}
}
