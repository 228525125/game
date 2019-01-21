package org.cx.game.validator;

import org.cx.game.command.Command;
import org.cx.game.command.InteriorCommand;
import org.cx.game.core.AbstractContext;
import org.cx.game.core.AbstractPlayer;
import org.cx.game.tools.CommonIdentifier;
import org.cx.game.tools.I18n;

/**
 * 执行命令前，验证是否具有控制权
 * @author chenxian
 *
 */
public class ControlPowerValidator extends Validator {

	private AbstractPlayer player = null;
	private Command command = null;
	
	public ControlPowerValidator(Command command, AbstractPlayer player) {
		// TODO Auto-generated constructor stub
		this.player = player;
		this.command = command;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		AbstractContext context = player.getContext();
		/*
		 * 创建游戏阶段不判断
		 */
		if(AbstractContext.Status_Prepare.equals(context.getStatus()) || AbstractContext.Status_Ready.equals(context.getStatus()))
			return true;
		else if(AbstractContext.Status_Start.equals(context.getStatus()) && (!this.command.isNeedControl() || player.equals(context.getControlPlayer()))) //start命令不判断
			return true;
		else{
			addMessage(I18n.getMessage(ControlPowerValidator.class.getName()));
			return false;
		}
	}
}
