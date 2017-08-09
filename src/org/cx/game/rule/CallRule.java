package org.cx.game.rule;

import java.util.Map;
import java.util.Observable;

import org.cx.game.action.ICall;
import org.cx.game.action.IDeath;
import org.cx.game.card.LifeCard;
import org.cx.game.core.IPlayer;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.widget.IPlace;
import org.cx.game.widget.LandformEffect;

public class CallRule implements IRule {

	private ICall call = null;
	
	public CallRule(ICall call) {
		// TODO Auto-generated constructor stub
		this.call = call;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (arg instanceof NotifyInfo) {
			NotifyInfo info = (NotifyInfo) arg;
			
			if(NotifyInfo.Card_LifeCard_Action_Call.equals(info.getType())){
				LifeCard owner = getOwner().getOwner();
				
				owner.getDeath().setStatus(IDeath.Status_Live);
				owner.getAttacked().setFightBack(true);
				
				IPlayer player = owner.getPlayer();
				player.addToResource(-owner.getCall().getConsume());
				
				player.addToRation(owner.getRation());
				
				/*
				 * 增加召唤计数器
				 */
				player.addCallCountOfPlay(1);
			}
		}
	}

	@Override
	public ICall getOwner() {
		// TODO Auto-generated method stub
		return this.call;
	}

}
