package org.cx.game.validator;

import java.util.Map;
import java.util.Map.Entry;

import org.cx.game.card.LifeCard;
import org.cx.game.tools.I18n;

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

		Map<String,Integer> res = life.getPlayer().getResource();
			
		for(Entry<String,Integer> entry : life.getCall().getConsume().entrySet()){
			String resType = entry.getKey();
			Integer resValue = res.get(resType);
			if(resValue<entry.getValue()){
				ret = false;
				addMessage(I18n.getMessage(CallConsumeValidator.class.getName()));
				break;
			}
		}	
	
		return ret;
	}
}
