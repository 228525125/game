package org.cx.game.policy.formula;

import org.cx.game.card.LifeCard;
import org.cx.game.tools.I18n;
import org.cx.game.validator.Validator;

public class NotStagnantFormula extends Validator implements IFormula {

	private LifeCard life = null;
	private Integer originPosition = null;
	
	public NotStagnantFormula(LifeCard life, Integer originPosition) {
		// TODO Auto-generated constructor stub
		this.life = life;
		this.originPosition = originPosition;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret = false;
		if(!this.originPosition.equals(life.getPosition())){
			ret = true;
		}else{
			ret = false;
			addMessage(I18n.getMessage(this));
		}
		return ret;
	}

}
