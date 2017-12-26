package org.cx.game.action;

import org.cx.game.widget.treasure.SkillCount;

public interface IUpgradeHero extends IUpgradeLife {

	public SkillCount getSkillCount();
	
	public void addToSkillCount(SkillCount sc);
}
