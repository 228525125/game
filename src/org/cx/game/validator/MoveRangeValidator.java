package org.cx.game.validator;

import org.cx.game.card.LifeCard;
import org.cx.game.tools.I18n;
import org.cx.game.widget.Place;

public class MoveRangeValidator extends Validator {

	private LifeCard life;
	private Place place;
	
	public MoveRangeValidator(LifeCard life, Place place) {
		// TODO Auto-generated constructor stub
		this.life = life;
		this.place = place;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		
		Integer curPosition = life.getPosition();
		Integer step = life.getPlayer().getContext().getGround().distance(curPosition, place.getPosition());
		Integer energy = life.getMove().getEnergy();
		Integer consume = life.getMove().getConsume();
		Integer range = energy/consume;         // 145/50 = 2;整数默认情况符合游戏规则
		
		if(range>=step)
			return true;
		else{
			addMessage(I18n.getMessage(MoveRangeValidator.class.getName()));
			return false;
		}
	}
}
