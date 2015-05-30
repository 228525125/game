package org.cx.game.validator;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.card.MagicCard;
import org.cx.game.command.CommandBuffer;
import org.cx.game.tools.I18n;

public class SelectMagicCardValidator extends SelectCardValidator {

	private MagicCard magic = null;
	
	public SelectMagicCardValidator(CommandBuffer buffer) {
		super(buffer);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret = super.validate(); 
		if(ret){
			ICard card = getCard();
			if (card instanceof MagicCard) {
				magic = (MagicCard) card;
				ret = true;
			}else{
				addMessage(I18n.getMessage(this));
				ret = false;
			}
		}
		
		return ret;
	}

	public MagicCard getMagic() {
		return magic;
	}

}
