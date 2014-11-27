package org.cx.game.validator;

import org.cx.game.card.LifeCard;
import org.cx.game.tools.I18n;

public class CallConsumeValidator extends Validator {

	private LifeCard life;
	
	public CallConsumeValidator(LifeCard life) {
		// TODO Auto-generated constructor stub
		this.life = life;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		if(life.getCall().getConsume()<=life.getPlayer().getResource())
			return true;
		else{
			addMessage(I18n.getMessage(this));
			return false;
		}
	}
}
