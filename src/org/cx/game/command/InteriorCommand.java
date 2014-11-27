package org.cx.game.command;

import org.cx.game.core.Context;
import org.cx.game.core.IPlayer;
import org.cx.game.validator.ControlPowerValidator;

public class InteriorCommand extends Command {

	protected IPlayer player;
	protected Context context;	
	protected CommandBuffer buffer;
	
	public InteriorCommand(IPlayer player) {
		super();
		// TODO Auto-generated constructor stub
		this.player = player;
		this.buffer = player.getCommandBuffer();
		this.context = player.getContext();
		addValidator(new ControlPowerValidator(player, this));        //判断是否有控制权
	}

}
