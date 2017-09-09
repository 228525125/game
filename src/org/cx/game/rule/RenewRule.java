package org.cx.game.rule;

import java.util.Observable;

import org.cx.game.action.Death;
import org.cx.game.action.IAttacked;
import org.cx.game.action.IRenew;
import org.cx.game.card.LifeCard;
import org.cx.game.core.IPlayer;
import org.cx.game.observer.NotifyInfo;

public class RenewRule implements IRule {

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		if (arg instanceof NotifyInfo) {
			NotifyInfo info = (NotifyInfo) arg;
			
			if(NotifyInfo.Card_LifeCard_Action_Renew.equals(info.getType())){
				IRenew renew = (IRenew) ((RuleGroup) o).getMessageSource();
				
				LifeCard owner = renew.getOwner(); 
				owner.getDeath().setStatus(Death.Status_Live);
				owner.getAttacked().setFightBack(true);
			}
		}
	}

}
