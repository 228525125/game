package org.cx.game.validator;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.card.TrickCard;
import org.cx.game.command.CommandBuffer;
import org.cx.game.tools.I18n;

public class SelectTrickCardValidator extends SelectCardValidator {

	private TrickCard trick = null;
	
	public SelectTrickCardValidator(CommandBuffer buffer) {
		// TODO Auto-generated constructor stub
		super(buffer);
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret = super.validate(); 
		if(ret){
			ICard card = getCard();
			if (card instanceof LifeCard) {
				trick = (TrickCard) card;
				ret = true;
			}else{
				addMessage(I18n.getMessage(this));
				ret = false;
			}
		}
		
		return ret;
	}

	protected TrickCard getCard() {
		return trick;
	}
}
