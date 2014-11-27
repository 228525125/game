package org.cx.game.command;

import org.cx.game.card.ICard;
import org.cx.game.card.TrickCard;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.CommandValidatorException;
import org.cx.game.exception.ValidatorException;
import org.cx.game.tools.Logger;
import org.cx.game.validator.SelectTrickCardValidator;
import org.cx.game.widget.IPlace;

public class SetupCommand extends InteriorCommand {

	public SetupCommand(IPlayer player) {
		// TODO Auto-generated constructor stub
		super(player);
		addValidator(new SelectTrickCardValidator(buffer));
	}
	
	@Override
	public void execute() throws ValidatorException {
		// TODO Auto-generated method stub
		super.execute();
		
		TrickCard trick = (TrickCard)buffer.getCard();
		IPlace place = (IPlace) parameter;
		trick.setup(place.getPosition());
	}
}
