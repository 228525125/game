package org.cx.game.rule;

import org.cx.game.intercepter.AbstractIntercepter;

public abstract class AbstractRule extends AbstractIntercepter implements IRule {

	private Object owner = null;
	
	@Override
	public void setOwner(Object owner) {
		// TODO Auto-generated method stub
		this.owner = owner;
	}

	@Override
	public Object getOwner() {
		// TODO Auto-generated method stub
		return this.owner;
	}
}
