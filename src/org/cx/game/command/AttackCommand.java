package org.cx.game.command;

import org.cx.game.card.LifeCard;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.CommandValidatorException;
import org.cx.game.exception.ValidatorException;
import org.cx.game.tools.Debug;
import org.cx.game.validator.AttackAtkValidator;
import org.cx.game.validator.AttackTauntValidator;
import org.cx.game.validator.AttackableValidator;
import org.cx.game.validator.SelectContainerValidator;
import org.cx.game.validator.SelectLifeCardNotHideValidator;

public class AttackCommand extends InteriorCommand {

	public AttackCommand(IPlayer player) {
		// TODO Auto-generated constructor stub
		super(player);
		addValidator(new SelectContainerValidator(player.getGround(),buffer));
		addValidator(new AttackableValidator(buffer));
		addValidator(new AttackAtkValidator(buffer));
	}
	
	@Override
	public void setParameter(Object parameter) {
		// TODO Auto-generated method stub
		super.setParameter(parameter);
		addValidator(new AttackTauntValidator(buffer, (LifeCard) parameter));
	}
	
	@Override
	public void execute() throws ValidatorException {
		// TODO Auto-generated method stub
		super.execute();
		
		LifeCard attack = (LifeCard) buffer.getCard();
		LifeCard attacked = (LifeCard) parameter;
		
		doValidator(new SelectLifeCardNotHideValidator(attacked));
		if(hasError())
			throw new CommandValidatorException(getErrors().getMessage());
		
		attack.attack(attacked);
		if(!Debug.isDebug)
			player.getContext().done();   //结束本回合
	}
}
