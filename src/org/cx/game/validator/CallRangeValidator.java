package org.cx.game.validator;

import java.util.List;

import org.cx.game.card.LifeCard;
import org.cx.game.core.IPlayer;
import org.cx.game.tools.I18n;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IPlace;
import org.cx.game.widget.building.Town;

/**
 * 判断call位置是否超出范围
 * @author chenxian
 *
 */
public class CallRangeValidator extends Validator {

	private Town town;
	private IPlace place;
	
	public CallRangeValidator(Town town, IPlace place) {
		// TODO Auto-generated constructor stub
		this.town = town;
		this.place = place;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret = false;
		IGround ground = town.getPlayer().getGround();
		if(Integer.valueOf(1).equals(ground.distance(town.getPosition(), place.getPosition())))
			ret = true;
		
		if(!ret)
			addMessage(I18n.getMessage(this));
		
		return ret;
	}
}
