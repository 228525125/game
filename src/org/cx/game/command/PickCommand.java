package org.cx.game.command;

import org.cx.game.card.LifeCard;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.ValidatorException;
import org.cx.game.validator.AttackableValidator;
import org.cx.game.validator.LifeCardMoveableValidator;
import org.cx.game.validator.MoveEnergyValidator;
import org.cx.game.validator.MoveTauntValidator;
import org.cx.game.validator.PickRangeValidator;
import org.cx.game.validator.SelectContainerValidator;
import org.cx.game.validator.SelectPlaceEmptyValidator;
import org.cx.game.validator.SelectPlaceExistTreasureValidator;
import org.cx.game.widget.IPlace;
import org.cx.game.widget.treasure.ITreasure;

public class PickCommand extends InteriorCommand {

	public PickCommand(IPlayer player) {
		super(player);
		// TODO Auto-generated constructor stub
		addValidator(new SelectContainerValidator(player.getGround(),buffer));
		addValidator(new AttackableValidator(buffer));
		addValidator(new MoveEnergyValidator(buffer));
		addValidator(new MoveTauntValidator(buffer));
	}
	
	@Override
	public void setParameter(Object parameter) {
		// TODO Auto-generated method stub
		super.setParameter(parameter);
		addValidator(new SelectPlaceExistTreasureValidator((IPlace) parameter));
		addValidator(new PickRangeValidator((LifeCard) buffer.getCard(), (IPlace) parameter));
	}
	
	@Override
	public void execute() throws ValidatorException {
		// TODO Auto-generated method stub
		super.execute();
		
		LifeCard life = (LifeCard) buffer.getCard();
		IPlace place = (IPlace) parameter;
		ITreasure treasure = place.getTreasure();
		life.pick(treasure);
	}

}
