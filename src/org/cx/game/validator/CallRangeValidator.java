package org.cx.game.validator;

import org.cx.game.tools.I18n;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IPlace;
import org.cx.game.widget.building.IBuilding;

/**
 * 判断call位置是否超出范围
 * @author chenxian
 *
 */
public class CallRangeValidator extends SelectBuildingTypeValidator {

	private IPlace place;
	private IBuilding town = null;
	
	public CallRangeValidator(IBuilding town, IPlace place) {
		// TODO Auto-generated constructor stub
		super(town, IBuilding.Town);
		this.town = town;
		this.place = place;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret = super.validate();
		
		if(ret){
			IGround ground = town.getPlayer().getGround();
			if(Integer.valueOf(1).equals(ground.distance(town.getPosition(), place.getPosition())))
				ret = true;
			else{
				addMessage(I18n.getMessage(this));
				ret = false;
			}
		}else{
			addMessage(I18n.getMessage(this));
		}
		
		return ret;
	}
}
