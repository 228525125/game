package org.cx.game.command;

import org.cx.game.card.LifeCard;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.ValidatorException;
import org.cx.game.tools.Debug;
import org.cx.game.validator.AttackAtkValidator;
import org.cx.game.validator.LifeCardActivateValidator;
import org.cx.game.validator.SelectContainerValidator;
import org.cx.game.validator.SelectLifeCardNotHideValidator;
import org.cx.game.validator.SelectLifeCardValidator;

public class AttackCommand extends InteriorCommand {

	public AttackCommand(IPlayer player) {
		// TODO Auto-generated constructor stub
		super(player);
		addValidator(new SelectLifeCardNotHideValidator(buffer));
		addValidator(new SelectContainerValidator(player.getGround(),buffer));
		addValidator(new LifeCardActivateValidator(buffer));
		addValidator(new AttackAtkValidator(buffer));
	}
	
	@Override
	public void execute() throws ValidatorException {
		// TODO Auto-generated method stub
		super.execute();
		
		LifeCard life = (LifeCard) buffer.getCard();
		
		life.attack((LifeCard) parameter);
				
		if(!Debug.isDebug)
			player.getContext().done();   //结束本回合
	}
}
