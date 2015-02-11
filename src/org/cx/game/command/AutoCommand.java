package org.cx.game.command;

import org.cx.game.card.LifeCard;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.ValidatorException;

public class AutoCommand extends InteriorCommand {

	public AutoCommand(IPlayer player) {
		super(player);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() throws ValidatorException {
		// TODO Auto-generated method stub
		super.execute();
		
		if(null!=this.context.getControlLife()){
			LifeCard life = context.getControlLife();
			life.getPolicy().execute();
		}else{
			this.player.getPolicy().execute();
		}
	}
}
