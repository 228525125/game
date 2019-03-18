package org.cx.game.command;

import org.cx.game.validator.ControlPowerValidator;

public class WithCacheCommand extends Command {

	protected CommandBuffer buffer;
	
	public WithCacheCommand(CommandBuffer buffer) {
		super();
		// TODO Auto-generated constructor stub
		this.buffer = buffer;

		addValidator(new ControlPowerValidator(this, buffer.getPlayer()));        //判断是否有控制权
	}
}
