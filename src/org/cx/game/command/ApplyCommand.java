package org.cx.game.command;

import org.cx.game.card.LifeCard;
import org.cx.game.card.MagicCard;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.ValidatorException;
import org.cx.game.validator.NeedConjurerValidator;
import org.cx.game.validator.SelectContainerValidator;
import org.cx.game.validator.SelectMagicCardValidator;

public class ApplyCommand extends InteriorCommand {

	public ApplyCommand(IPlayer player) {
		super(player);
		// TODO Auto-generated constructor stub
		addValidator(new SelectMagicCardValidator(buffer));
		addValidator(new SelectContainerValidator(player.getUseCard(),buffer));
	}
	
	@Override
	public void execute() throws ValidatorException {
		// TODO Auto-generated method stub
		super.execute();
		
		MagicCard magic = (MagicCard) buffer.getCard();
		
		doValidator(new NeedConjurerValidator(magic, buffer));
		
		if(magic.needConjurer()){
			magic.setConjurer((LifeCard) buffer.lastCard());
		}
		
		magic.apply(new Object[]{parameter});
		
		magic.chuck();
	}

}
