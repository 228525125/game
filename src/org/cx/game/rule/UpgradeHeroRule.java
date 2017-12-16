package org.cx.game.rule;

import org.cx.game.action.IUpgradeHero;
import org.cx.game.action.UpgradeHero;

public class UpgradeHeroRule extends UpgradeLifeRule implements IRule {

	@Override
	public void after(Object[] args) {
		// TODO Auto-generated method stub
		//super.after(args);      因为UpgadeLifeRule同样存在，为避免重复
		getOwner().addToSkillCount(1);
	}
	
	@Override
	public Class getInterceptable() {
		// TODO Auto-generated method stub
		return UpgradeHero.class;
	}
	
	@Override
	public UpgradeHero getOwner() {
		// TODO Auto-generated method stub
		return (UpgradeHero) super.getOwner();
	}
}
