package org.cx.game.rule;

import java.util.Map;
import java.util.Observable;

import org.cx.game.action.IAttacked;
import org.cx.game.action.IConjure;
import org.cx.game.card.LifeCard;
import org.cx.game.observer.NotifyInfo;

public class ConjureRule implements IRule {
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		if (arg instanceof NotifyInfo) {
			NotifyInfo info = (NotifyInfo) arg;
			
			if(NotifyInfo.Card_LifeCard_Action_Conjure.equals(info.getType())){
				IConjure conjure = (IConjure) ((RuleGroup) o).getMessageSource();
				
				conjure.getOwner().getAttack().setAttackable(false);
			}
		}
	}

}
