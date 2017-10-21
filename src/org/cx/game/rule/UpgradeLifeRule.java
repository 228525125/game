package org.cx.game.rule;

import org.cx.game.action.ILifeUpgrade;
import org.cx.game.action.IUpgrade;
import org.cx.game.action.LifeUpgrade;
import org.cx.game.card.LifeCard;

public class UpgradeLifeRule extends Rule implements IRule {

	@Override
	public String getIntercepterMethod() {
		// TODO Auto-generated method stub
		return "action";
	}
	
	@Override
	public void after(Object[] args) {
		// TODO Auto-generated method stub
		
		ILifeUpgrade upgrade = getOwner();
		
		LifeCard life = upgrade.getOwner();
		Integer hplimit = life.getDeath().getHplimit();
		Integer atk = life.getAttack().getAtk();
		Integer def = life.getAttacked().getDef();
		Integer consume = life.getCall().getConsume();
		
		life.getDeath().setHplimit(hplimit+hplimit*IUpgrade.LifeCardRiseRatio/100);    //增加HP上限
		life.getDeath().addToHp(hplimit*IUpgrade.LifeCardRiseRatio/100);               //增加HP
		life.getAttack().addToAtk(atk*IUpgrade.LifeCardRiseRatio/100);                 //增加Atk
		life.getAttacked().addToDef(def*IUpgrade.LifeCardRiseRatio/100);               //增加Def
		
		life.getCall().addToConsume(consume*IUpgrade.LifeCardRiseRatio/100);           //增加call消耗
	}
	
	@Override
	public Class getInterceptable() {
		// TODO Auto-generated method stub
		return ILifeUpgrade.class;
	}
	
	@Override
	public ILifeUpgrade getOwner() {
		// TODO Auto-generated method stub
		return (ILifeUpgrade) super.getOwner();
	}

}
