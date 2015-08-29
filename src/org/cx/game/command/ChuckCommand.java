package org.cx.game.command;

import org.cx.game.card.ICard;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.CommandValidatorException;
import org.cx.game.exception.ValidatorException;
import org.cx.game.validator.SelectCardValidator;

public class ChuckCommand extends InteriorCommand {

	public ChuckCommand(IPlayer player) {
		// TODO Auto-generated constructor stub
		super(player);
		addValidator(new SelectCardValidator(buffer));
	}
	
	@Override
	public void execute() throws ValidatorException {
		// TODO Auto-generated method stub
		super.execute();
		
		ICard card = buffer.getCard();
		card.chuck();
	}
}
