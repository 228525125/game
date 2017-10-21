package org.cx.game.rule;

import java.util.Observable;

import org.cx.game.action.IActivate;
import org.cx.game.action.IApply;
import org.cx.game.core.IPlayer;
import org.cx.game.observer.NotifyInfo;

public class ApplyRule extends Rule implements IRule {

	@Override
	public String getIntercepterMethod() {
		// TODO Auto-generated method stub
		return "action";
	}
	
	@Override
	public void after(Object[] args) {
		// TODO Auto-generated method stub
		IApply apply = getOwner();
		IPlayer player = apply.getOwner().getPlayer();
		player.addToResource(-apply.getConsume());
	}
	
	@Override
	public IApply getOwner() {
		// TODO Auto-generated method stub
		return (IApply) super.getOwner();
	}

	@Override
	public Class getInterceptable() {
		// TODO Auto-generated method stub
		return IApply.class;
	}

}
