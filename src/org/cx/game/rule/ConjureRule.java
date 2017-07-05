package org.cx.game.rule;

import java.util.Map;
import java.util.Observable;

import org.cx.game.action.IConjure;
import org.cx.game.card.LifeCard;
import org.cx.game.observer.NotifyInfo;

public class ConjureRule implements IRule {

	private IConjure conjure = null;
	
	public ConjureRule(IConjure conjure) {
		// TODO Auto-generated constructor stub
		this.conjure = conjure;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (arg instanceof NotifyInfo) {
			NotifyInfo info = (NotifyInfo) arg;
			
			if(NotifyInfo.Card_LifeCard_Action_Conjure.equals(info.getType())){
				this.conjure.getOwner().getAttack().setAttackable(false);
			}
		}
	}

	@Override
	public Object getOwner() {
		// TODO Auto-generated method stub
		return null;
	}

}
