package org.cx.game.action;

public interface IUpgradeHero extends IUpgradeLife {

	public Integer getSkillCount();
	
	public void setSkillCount(Integer skillCount);
	
	public void addToSkillCount(Integer skillCount);
}
