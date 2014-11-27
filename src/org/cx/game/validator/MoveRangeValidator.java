package org.cx.game.validator;

import org.cx.game.card.LifeCard;
import org.cx.game.tools.I18n;
import org.cx.game.widget.IPlace;

public class MoveRangeValidator extends Validator {

	private LifeCard life;
	private IPlace place;
	
	public MoveRangeValidator(LifeCard life, IPlace place) {
		// TODO Auto-generated constructor stub
		this.life = life;
		this.place = place;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		
		Integer curPosition = life.getContainerPosition();
		Integer step = life.getPlayer().getGround().distance(curPosition, place.getPosition());
		Integer range = life.getMove().getEnergy()/life.getMove().getConsume();    // 145/50 = 2;整数默认情况符合游戏规则
		
		if(range>=step)
			return true;
		else{
			addMessage(I18n.getMessage(this));
			return false;
		}
	}
}
