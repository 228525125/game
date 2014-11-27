package org.cx.game.command;

import org.cx.game.card.LifeCard;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.ValidatorException;
import org.cx.game.validator.NullPointValidator;
import org.cx.game.validator.SelectLifeCardValidator;
import org.cx.game.widget.IPlace;

public class SwapCommand extends InteriorCommand {

	public SwapCommand(IPlayer player) {
		// TODO Auto-generated constructor stub
		super(player);
		addValidator(new SelectLifeCardValidator(buffer));		
	}
	
	@Override
	public void execute() throws ValidatorException {
		// TODO Auto-generated method stub
		super.execute();
		
		LifeCard swap = (LifeCard)buffer.getCard();		
		LifeCard swaped = (LifeCard) parameter;
		swap.swap(swaped);
	}
	
}
