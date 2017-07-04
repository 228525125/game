package org.cx.game.validator;

import org.cx.game.card.LifeCard;
import org.cx.game.core.IPlayer;
import org.cx.game.tools.I18n;

public class RationLimitValidator extends Validator {
	
	private LifeCard life = null;

	public RationLimitValidator(LifeCard life) {
		// TODO Auto-generated constructor stub
		this.life = life;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		IPlayer player = life.getPlayer();
		Integer ration = player.getRationLimit()-player.getRation();
		if(ration>=life.getRation())
			return true;
		else{
			addMessage(I18n.getMessage(this));
			return false;
		}
	}
}
