package org.cx.game.validator;

import java.util.List;

import org.cx.game.card.LifeCard;
import org.cx.game.core.IPlayer;
import org.cx.game.tools.I18n;
import org.cx.game.widget.IPlace;

/**
 * 判断call位置是否超出范围
 * @author chenxian
 *
 */
public class CallRangeValidator extends Validator {

	private LifeCard life;
	private IPlace place;
	
	public CallRangeValidator(LifeCard life, IPlace place) {
		// TODO Auto-generated constructor stub
		this.life = life;
		this.place = place;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret = false;
		IPlayer player = life.getPlayer();
		List<Integer> entryList = player.getGround().getEntryList(life);
		if(entryList.contains(place.getPosition()))
			ret = true;
		
		if(!ret)
			addMessage(I18n.getMessage(this));
		
		return ret;
	}
}
