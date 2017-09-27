package org.cx.game.policy.formula;

import org.cx.game.card.LifeCard;
import org.cx.game.card.buff.AttackLockBuff;
import org.cx.game.tools.I18n;
import org.cx.game.validator.Validator;

public class NotLockFormula extends Validator implements IFormula {

	private LifeCard life = null;
	
	public NotLockFormula(LifeCard life) {
		// TODO Auto-generated constructor stub
		this.life = life;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret = false;
		
		if(!this.life.containsBuff(AttackLockBuff.class)){
			ret = true;
		}else{
			addMessage(I18n.getMessage(this));
			ret = false;
		}
		
		return ret;
	}

}
