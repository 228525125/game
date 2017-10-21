package org.cx.game.rule;

import org.cx.game.action.IUpgrade;
import org.cx.game.action.LifeUpgrade;
import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.ISkill;

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
		LifeUpgrade up = (LifeUpgrade) life.getUpgrade();
		up.addToSkillCount(-upgrade.getConsume());
	}
	
	@Override
	public Class getInterceptable() {
		// TODO Auto-generated method stub
		return IUpgrade.class;
	}
	
	@Override
	public IUpgrade getOwner() {
		// TODO Auto-generated method stub
		return (IUpgrade) super.getOwner();
	}

}
