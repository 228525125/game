package org.cx.game.rule;

import java.util.Observable;

import org.cx.game.action.Death;
import org.cx.game.action.IAttacked;
import org.cx.game.action.IRenew;
import org.cx.game.card.LifeCard;
import org.cx.game.core.IPlayer;
import org.cx.game.observer.NotifyInfo;

public class RenewRule extends Rule implements IRule {
	
	@Override
	public String getIntercepterMethod() {
		// TODO Auto-generated method stub
		return "action";
	}
	
	@Override
	public void after(Object[] args) {
		// TODO Auto-generated method stub
		IRenew renew = getOwner();
		
		LifeCard owner = renew.getOwner(); 
		owner.getDeath().setStatus(Death.Status_Live);
		owner.getAttacked().setFightBack(true);
	}
	
	@Override
	public Class getInterceptable() {
		// TODO Auto-generated method stub
		return IRenew.class;
	}
	
	@Override
	public IRenew getOwner() {
		// TODO Auto-generated method stub
		return (IRenew) super.getOwner();
	}

}
