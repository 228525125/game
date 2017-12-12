package org.cx.game.validator;

import java.util.Map;
import java.util.Map.Entry;

import org.cx.game.card.MagicCard;
import org.cx.game.tools.I18n;

public class ApplyConsumeValidator extends Validator {

	private MagicCard magic;
	
	public ApplyConsumeValidator(MagicCard magic) {
		// TODO Auto-generated constructor stub
		this.magic = magic;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret = true;

		Map<String,Integer> res = magic.getPlayer().getResource();
			
		for(Entry<String,Integer> entry : magic.getConsume().entrySet()){
			String resType = entry.getKey();
			Integer resValue = res.get(resType);
			if(resValue<entry.getValue()){
				ret = false;
				addMessage(I18n.getMessage(ApplyConsumeValidator.class.getName()));
				break;
			}
		}
			
		return ret;
	}
	
}
