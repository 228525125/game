package org.cx.game.validator;

import org.cx.game.card.ICard;
import org.cx.game.card.MagicCard;
import org.cx.game.tools.I18n;
import org.cx.game.widget.IPlace;

public class ApplyRangeValidator extends Validator {

	private MagicCard magic;
	private Integer destPos;
	
	public ApplyRangeValidator(MagicCard magic, Integer destPos) {
		// TODO Auto-generated constructor stub
		this.magic = magic;
		this.destPos = destPos;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		
		Integer curPosition = magic.getConjurer().getContainerPosition();
		Integer step = magic.getPlayer().getGround().distance(curPosition, destPos);
		Integer range = magic.getConjureRange();
		
		if(range>=step)
			return true;
		else{
			addMessage(I18n.getMessage(this));
			return false;
		}
	}
}
