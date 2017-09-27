package org.cx.game.action;

import org.cx.game.card.LifeCard;

public class LifeUpgradeDecorator extends UpgradeDecorator implements
		ILifeUpgrade {

	private ILifeUpgrade upgrade = null;
	
	public LifeUpgradeDecorator(ILifeUpgrade upgrade) {
		super(upgrade);
		// TODO Auto-generated constructor stub
		this.upgrade = upgrade;
	}

	@Override
	public Integer getEmpiricValue() {
		// TODO Auto-generated method stub
		return this.upgrade.getEmpiricValue();
	}

	@Override
	public void setEmpiricValue(Integer empiricValue) {
		// TODO Auto-generated method stub
		this.upgrade.setEmpiricValue(empiricValue);
	}

	@Override
	public void addToEmpiricValue(Integer empiricValue) {
		// TODO Auto-generated method stub
		this.upgrade.addToEmpiricValue(empiricValue);
	}

	@Override
	public Integer getSkillCount() {
		// TODO Auto-generated method stub
		return this.upgrade.getSkillCount();
	}

	@Override
	public void setSkillCount(Integer skillCount) {
		// TODO Auto-generated method stub
		this.upgrade.setSkillCount(skillCount);
	}

	@Override
	public void addToSkillCount(Integer skillCount) {
		// TODO Auto-generated method stub
		this.upgrade.addToSkillCount(skillCount);
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
	public Integer getConsume() {
		// TODO Auto-generated method stub
		return this.upgrade.getConsume();
	}
	
	@Override
	public void setConsume(Integer consume) {
		// TODO Auto-generated method stub
		this.upgrade.setConsume(consume);
	}
	
	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return this.upgrade.getOwner();
	}

}
