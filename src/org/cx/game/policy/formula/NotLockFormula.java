package org.cx.game.policy.formula;

import org.cx.game.card.LifeCard;
import org.cx.game.tools.I18n;

public class NotLockFormula extends LockFormula {

	public NotLockFormula(LifeCard life) {
		super(life);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret = super.validate();
		if(!ret){
			ret = true;
		}else{
			addMessage(I18n.getMessage(this));
			ret = false;
		}
		return ret;
	}

}
