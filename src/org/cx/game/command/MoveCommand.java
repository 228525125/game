package org.cx.game.command;

import org.cx.game.card.LifeCard;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.ValidatorException;
import org.cx.game.validator.LifeCardActivateValidator;
import org.cx.game.validator.LifeCardMoveableValidator;
import org.cx.game.validator.MoveRangeValidator;
import org.cx.game.validator.MoveTauntValidator;
import org.cx.game.validator.SelectContainerValidator;
import org.cx.game.validator.SelectPlaceEmptyValidator;
import org.cx.game.widget.IPlace;

public class MoveCommand extends InteriorCommand {

	public MoveCommand(IPlayer player) {
		super(player);
		// TODO Auto-generated constructor stub
		addValidator(new SelectContainerValidator(player.getContext().getGround(),buffer));
		addValidator(new LifeCardMoveableValidator(buffer));
		addValidator(new MoveTauntValidator(buffer));
	}
	
	@Override
	public void setParameter(Object parameter) {
		// TODO Auto-generated method stub
		super.setParameter(parameter);
		addValidator(new SelectPlaceEmptyValidator((IPlace) parameter, true));
		addValidator(new MoveRangeValidator((LifeCard) buffer.getCard(), (IPlace) parameter));
	}
	
	@Override
	public void execute() throws ValidatorException {
		// TODO Auto-generated method stub
		super.execute();
		
		LifeCard life = (LifeCard) buffer.getCard();
		IPlace place = (IPlace) parameter;
		life.move(place);
	}
}
