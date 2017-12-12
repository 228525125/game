package org.cx.game.validator;

import org.cx.game.card.LifeCard;
import org.cx.game.tools.I18n;

/**
 * 当补充兵源时，验证兵源与军队兵种的一致性；
 * @author chenxian
 *
 */
public class CallUnitEqualValidator extends Validator {

	private LifeCard unit = null;
	private LifeCard call = null;
	
	public CallUnitEqualValidator(LifeCard unit, LifeCard call) {
		// TODO Auto-generated constructor stub
		this.unit = unit;
		this.call = call;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		if(unit.getId().equals(call.getId()))
			return true;
		else{
			addMessage(I18n.getMessage(CallUnitEqualValidator.class.getName()));
			return false;
		}
	}
}
