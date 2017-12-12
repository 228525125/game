package org.cx.game.validator;

import org.cx.game.card.ICard;
import org.cx.game.command.CommandBuffer;
import org.cx.game.tools.I18n;

/**
 * 验证缓存中是否存在card
 * @author chenxian
 *
 */
public class SelectCardValidator extends Validator {

	protected CommandBuffer buffer = null;
	private ICard card = null;
	
	public SelectCardValidator(CommandBuffer buffer) {
		// TODO Auto-generated constructor stub
		this.buffer = buffer;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		this.card = buffer.getCard(); 
		if(null!= card)
			return true;
		else{
			addMessage(I18n.getMessage(SelectCardValidator.class.getName()));
			return false;
		}
	}

	protected ICard getCard() {
		return card;
	}
	
	
}
