package org.cx.game.validator;

import java.util.List;

import org.cx.game.card.MagicCard;
import org.cx.game.tools.I18n;
import org.cx.game.widget.IGround;

public class ApplyRangeValidator extends Validator {

	private MagicCard magic;
	private Integer destPos;
	private IGround ground;
	
	public ApplyRangeValidator(MagicCard magic, Integer destPos, IGround ground) {
		// TODO Auto-generated constructor stub
		this.magic = magic;
		this.destPos = destPos;
		this.ground = ground;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		List<Integer> range = magic.getApplyRange(ground);
		
		if(range.contains(destPos))
			return true;
		else{
			addMessage(I18n.getMessage(ApplyRangeValidator.class.getName()));
			return false;
		}
	}
}
