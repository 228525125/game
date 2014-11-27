package org.cx.game.validator;

import org.cx.game.core.IPlayer;
import org.cx.game.tools.I18n;
import org.cx.game.widget.IPlace;

/**
 * 判断call位置是否超出范围
 * @author chenxian
 *
 */
public class CallRangeValidator extends Validator {

	private IPlayer player;
	private IPlace place;
	
	public CallRangeValidator(IPlayer player, IPlace place) {
		// TODO Auto-generated constructor stub
		this.player = player;
		this.place = place;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret = false;
		for(Integer campPos : player.getGround().getCampPosition(player)){
			if(3>=player.getGround().easyDistance(place.getPosition(), campPos)){
				ret = true;
				break;
			}
		}
		
		if(!ret)
			addMessage(I18n.getMessage(this));
		
		return ret;
	}
}
