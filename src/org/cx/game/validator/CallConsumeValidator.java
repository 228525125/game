package org.cx.game.validator;

import java.util.Map;
import java.util.Map.Entry;

import org.cx.game.card.LifeCard;
import org.cx.game.tools.I18n;
import org.cx.game.widget.treasure.IResource;

public class CallConsumeValidator extends Validator {

	private LifeCard life;
	private Integer number;
	
	public CallConsumeValidator(LifeCard life, Integer number) {
		// TODO Auto-generated constructor stub
		this.life = life;
		this.number = number;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret = true;

		IResource res = life.getPlayer().getResource();
			
		if(res.absoluteLessThan(life.getCall().getConsume())){
			ret = false;
			addMessage(I18n.getMessage(CallConsumeValidator.class.getName()));
		}	
	
		return ret;
	}
}
