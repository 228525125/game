package org.cx.game.rule;

import java.util.Map;
import java.util.Observable;

import org.cx.game.action.IAttacked;
import org.cx.game.action.IConjure;
import org.cx.game.card.LifeCard;
import org.cx.game.observer.NotifyInfo;

public class ConjureRule extends Rule implements IRule {
	
	@Override
	public String getIntercepterMethod() {
		// TODO Auto-generated method stub
		return "action";
	}
	
	@Override
	public void after(Object[] args) {
		// TODO Auto-generated method stub
		IConjure conjure = getOwner();
		conjure.getOwner().getAttack().setAttackable(false);
	}

	@Override
	public IConjure getOwner() {
		// TODO Auto-generated method stub
		return (IConjure) super.getOwner();
	}

	@Override
	public Class getInterceptable() {
		// TODO Auto-generated method stub
		return IConjure.class;
	}
}
