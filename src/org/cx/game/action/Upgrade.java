package org.cx.game.action;

import org.cx.game.out.JsonOut;

public abstract class Upgrade extends Action implements IUpgrade {

	private Integer level = 1;
	private Integer waitBout = 0;

	public Upgrade() {
		// TODO Auto-generated constructor stub
		addObserver(new JsonOut());
	}
	
	@Override
	public Integer getLevel() {
		// TODO Auto-generated method stub
		return this.level;
	}

	@Override
	public void setLevel(Integer level) {
		// TODO Auto-generated method stub
		this.level = level;
	}
	
	@Override
	public Integer getWaitBout() {
		// TODO Auto-generated method stub
		return this.waitBout;
	}
	
	@Override
	public void setWaitBout(Integer bout) {
		// TODO Auto-generated method stub
		this.waitBout = bout;
	}

}
