package org.cx.game.command;

import org.cx.game.core.AbstractContext;
import org.cx.game.core.AbstractPlayer;
import org.cx.game.validator.ControlPowerValidator;

public class InteriorCommand extends Command {

	protected AbstractPlayer player;
	protected AbstractContext context;	
	protected CommandBuffer buffer;
	
	public InteriorCommand(AbstractPlayer player) {
		super();
		// TODO Auto-generated constructor stub
		this.player = player;
		this.buffer = player.getCommandBuffer();
		this.context = player.getContext();

		addValidator(new ControlPowerValidator(this, player));        //判断是否有控制权
	}

}
