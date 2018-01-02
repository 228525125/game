package org.cx.game.command;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.card.MagicCard;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.CommandValidatorException;
import org.cx.game.exception.ValidatorException;
import org.cx.game.validator.ApplyRangeValidator;
import org.cx.game.validator.NeedConjurerValidator;
import org.cx.game.validator.SelectContainerValidator;
import org.cx.game.validator.SelectMagicCardValidator;
import org.cx.game.widget.IPlace;

public class ApplyCommand extends InteriorCommand {

	public ApplyCommand(IPlayer player) {
		super(player);
		// TODO Auto-generated constructor stub
		addValidator(new SelectMagicCardValidator(buffer));
	}
	
	@Override
	public void execute() throws ValidatorException {
		// TODO Auto-generated method stub
		super.execute();
		
		MagicCard magic = (MagicCard) buffer.getCard();
		
		magic.apply(new Object[]{parameter});
		
		magic.chuck();
	}

}
