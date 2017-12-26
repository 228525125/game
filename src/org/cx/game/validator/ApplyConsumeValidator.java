package org.cx.game.validator;

import java.util.Map;
import java.util.Map.Entry;

import org.cx.game.card.MagicCard;
import org.cx.game.tools.I18n;
import org.cx.game.widget.treasure.IResource;

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

		IResource res = magic.getPlayer().getResource();
			
		if(res.absoluteLessThan(magic.getConsume())){
			ret = false;
			addMessage(I18n.getMessage(ApplyConsumeValidator.class.getName()));
		}
			
		return ret;
	}
	
}
