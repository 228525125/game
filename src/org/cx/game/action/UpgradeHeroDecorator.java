package org.cx.game.action;

public class UpgradeHeroDecorator extends UpgradeLifeDecorator implements
		IUpgradeHero {

	private IUpgradeHero upgrade = null;
	
	public UpgradeHeroDecorator(IUpgradeHero upgrade) {
		super(upgrade);
		// TODO Auto-generated constructor stub
		this.upgrade = upgrade;
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

}
