package org.cx.game.rule;

import org.cx.game.action.Apply;
import org.cx.game.action.IApply;
import org.cx.game.core.IPlayer;

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
		
		/*
		 * 扣减资源
		 */
		player.addToResource(apply.getConsume());
	}
	
	@Override
	public Apply getOwner() {
		// TODO Auto-generated method stub
		return (Apply) super.getOwner();
	}
	
	@Override
	public Class getInterceptable() {
		// TODO Auto-generated method stub
		return Apply.class;
	}
}
