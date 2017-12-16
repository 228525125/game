package org.cx.game.validator;

import org.cx.game.action.IPick;
import org.cx.game.card.LifeCard;
import org.cx.game.tools.I18n;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IPlace;

/**
 * 验证拾取距离
 * @author chenxian
 *
 */
public class PickRangeValidator extends Validator {

	private IPlace place = null;
	private LifeCard life = null;
	
	public PickRangeValidator(LifeCard life, IPlace place) {
		// TODO Auto-generated constructor stub
		this.place = place;
		this.life = life;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret = true;
		IGround ground = this.life.getPlayer().getContext().getGround();
		Integer distance = ground.distance(this.life.getPosition(), this.place.getPosition());
		if(IPick.Pick_Range_Defautl<distance){
			ret = false;
			addMessage(I18n.getMessage(PickRangeValidator.class.getName()));
		}
	
		return ret;
	}
}
