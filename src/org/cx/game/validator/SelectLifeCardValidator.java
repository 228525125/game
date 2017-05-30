package org.cx.game.validator;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.command.CommandBuffer;
import org.cx.game.tools.I18n;

/**
 * 验证缓存中是否存在LifeCard
 * @author chenxian
 *
 */
public class SelectLifeCardValidator extends SelectCardValidator {
	
	private LifeCard life = null;
	
	public SelectLifeCardValidator(CommandBuffer buffer) {
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
				life = (LifeCard) card;
				ret = true;
			}else{
				addMessage(I18n.getMessage(this));
				ret = false;
			}
		}
		
		return ret;
	}

	public LifeCard getLifeCard() {
		return life;
	}
}
