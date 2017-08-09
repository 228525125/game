package org.cx.game.policy.formula;

import org.cx.game.card.LifeCard;
import org.cx.game.tools.I18n;

public class NotStagnantFormula extends StagnantFormula {

	public NotStagnantFormula(LifeCard life, Integer originPosition) {
		super(life, originPosition);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret = super.validate();
		if(!ret){
			ret = true;
		}else{
			ret = false;
			addMessage(I18n.getMessage(this));
		}
		
		return ret;
	}

}
