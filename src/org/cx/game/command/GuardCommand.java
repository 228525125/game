package org.cx.game.command;

import org.cx.game.card.LifeCard;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.ValidatorException;
import org.cx.game.validator.LifeCardActivateValidator;

public class GuardCommand extends InteriorCommand {

	public GuardCommand(IPlayer player) {
		// TODO Auto-generated constructor stub
		super(player);
		
		addValidator(new LifeCardActivateValidator(buffer));
	}
	
	@Override
	public void execute() throws ValidatorException {
		// TODO Auto-generated method stub
		super.execute();
		
		LifeCard life = (LifeCard) buffer.getCard();
		life.activate(false);
	}
}
