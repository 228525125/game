package org.cx.game.rule;

import java.util.Map;
import java.util.Observable;

import org.cx.game.action.IAttacked;
import org.cx.game.action.ICall;
import org.cx.game.action.IDeath;
import org.cx.game.card.LifeCard;
import org.cx.game.core.IPlayer;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.widget.IContainer;
import org.cx.game.widget.IPlace;
import org.cx.game.widget.LandformEffect;

public class CallRule implements IRule {
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		if (arg instanceof NotifyInfo) {
			NotifyInfo info = (NotifyInfo) arg;
			
			if(NotifyInfo.Card_LifeCard_Action_Call.equals(info.getType())){
				ICall call = (ICall) ((RuleGroup) o).getMessageSource();
				
				Map bean = (Map) info.getInfo();

				LifeCard owner = call.getOwner();
				
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

}
