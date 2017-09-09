package org.cx.game.action;

public abstract class Upgrade extends Action implements IUpgrade {

	private Integer level = 1;
	
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

}
