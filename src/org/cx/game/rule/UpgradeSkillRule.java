package org.cx.game.rule;

import org.cx.game.action.IUpgradeHero;
import org.cx.game.action.IUpgradeLife;
import org.cx.game.action.IUpgradeSkill;
import org.cx.game.action.IUpgrade;
import org.cx.game.action.UpgradeLife;
import org.cx.game.action.UpgradeSkill;
import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.ISkill;
import org.cx.game.core.IPlayer;

public class UpgradeSkillRule extends Rule implements IRule {

	@Override
	public String getIntercepterMethod() {
		// TODO Auto-generated method stub
		return "action";
	}
	
	@Override
	public void after(Object[] args) {
		// TODO Auto-generated method stub
		IUpgrade upgrade = getOwner();
		
		ISkill skill = (ISkill) upgrade.getOwner();
		LifeCard life = skill.getOwner();
		IUpgradeHero up = (IUpgradeHero) life.getUpgrade();
		Integer skillCountReq = up.getRequirement().get(IPlayer.SkillCount);
		up.addToSkillCount(skillCountReq);
	}
	
	@Override
	public Class getInterceptable() {
		// TODO Auto-generated method stub
		return UpgradeSkill.class;
	}
	
	@Override
	public UpgradeSkill getOwner() {
		// TODO Auto-generated method stub
		return (UpgradeSkill) super.getOwner();
	}

}
