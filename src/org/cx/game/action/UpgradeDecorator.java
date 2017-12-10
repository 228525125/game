package org.cx.game.action;

import java.util.Map;

public class UpgradeDecorator extends ActionDecorator implements IUpgrade {

	private IUpgrade upgrade = null;
	
	public UpgradeDecorator(IUpgrade upgrade) {
		super(upgrade);
		// TODO Auto-generated constructor stub
		this.upgrade = upgrade;
	}

	@Override
	public Integer getLevel() {
		// TODO Auto-generated method stub
		return this.upgrade.getLevel();
	}

	@Override
	public void setLevel(Integer level) {
		// TODO Auto-generated method stub
		this.upgrade.setLevel(level);
	}

	@Override
	public Map<String,Integer> getRequirement() {
		// TODO Auto-generated method stub
		return this.upgrade.getRequirement();
	}
	
	@Override
	public void setRequirement(Map<String,Integer> consume) {
		// TODO Auto-generated method stub
		this.upgrade.setRequirement(consume);
	}

	@Override
	public void updateRequirement() {
		// TODO Auto-generated method stub
		this.upgrade.updateRequirement();
	}

	@Override
	public Integer getLevelLimit() {
		// TODO Auto-generated method stub
		return this.upgrade.getLevelLimit();
	}

	@Override
	public void setLevelLimit(Integer levelLimit) {
		// TODO Auto-generated method stub
		this.upgrade.setLevelLimit(levelLimit);
	}

}
