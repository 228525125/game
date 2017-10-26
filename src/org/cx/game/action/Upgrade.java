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
		
		if(null!=getOwner())
			updateStandard();			
		
	}
	
	protected Integer standard = 1;
	
	@Override
	public Integer getStandard() {
		// TODO Auto-generated method stub
		return standard;
	}
	
	@Override
	public void setStandard(Integer standard) {
		// TODO Auto-generated method stub
		this.standard = standard;
	}

}
