package org.cx.game.validator;

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
		if(magic.getApply().getConsume()<=magic.getPlayer().getResource())
			return true;
		else{
			addMessage(I18n.getMessage(this));
			return false;
		}
	}
	
}
