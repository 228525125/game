package org.cx.game.command;

import org.cx.game.card.LifeCard;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.ValidatorException;
import org.cx.game.validator.SelectLifeCardValidator;

public class ChuckCommand extends InteriorCommand {

	public ChuckCommand(IPlayer player) {
		// TODO Auto-generated constructor stub
		super(player);
		addValidator(new SelectLifeCardValidator(buffer));
	}
	
	@Override
	public void execute() throws ValidatorException {
		// TODO Auto-generated method stub
		super.execute();
		
		LifeCard life = buffer.getCard();
		life.chuck();
	}
}
