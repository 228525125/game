package org.cx.game.validator;

import org.cx.game.card.LifeCard;
import org.cx.game.card.MagicCard;
import org.cx.game.command.CommandBuffer;
import org.cx.game.tools.I18n;

public class NeedConjurerValidator extends Validator {

	private MagicCard magic = null;
	private CommandBuffer buffer = null;
	
	public NeedConjurerValidator(MagicCard magic, CommandBuffer buffer) {
		// TODO Auto-generated constructor stub
		this.magic = magic;
		this.buffer = buffer;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret = true;
		if(magic.needConjurer()){
			if (buffer.lastCard() instanceof LifeCard) {
				ret = true;
			}else{
				addMessage(I18n.getMessage(this));
				ret = false;
			}
		}
		
		return ret;
	}

}
