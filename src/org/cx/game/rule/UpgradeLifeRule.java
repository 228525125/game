package org.cx.game.rule;

import org.cx.game.action.IDeath;
import org.cx.game.action.IUpgradeHero;
import org.cx.game.action.IUpgradeLife;
import org.cx.game.action.IUpgrade;
import org.cx.game.action.UpgradeLife;
import org.cx.game.card.HeroCard;
import org.cx.game.card.LifeCard;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.widget.building.IBuilding;
import org.cx.game.widget.building.IProduct;

public class UpgradeLifeRule extends Rule implements IRule {

	@Override
	public String getIntercepterMethod() {
		// TODO Auto-generated method stub
		return "action";
	}
	
	private Integer hpLimit = 0;
	private Integer empRequirement = 0;
	
	@Override
	public void before(Object[] args) {
		// TODO Auto-generated method stub
		IDeath death = getOwner().getOwner().getDeath();
		this.hpLimit = death.getHpLimit();
		this.empRequirement = getOwner().getRequirement().get(IPlayer.EmpiricValue);
	}
	
	@Override
	public void after(Object[] args) {
		// TODO Auto-generated method stub
		LifeCard life = getOwner().getOwner();
		IDeath death = life.getDeath();
		
		try {
			death.addToHp(death.getHpLimit()-hpLimit);
		} catch (RuleValidatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		IUpgradeLife upgrade = getOwner();
		upgrade.addToEmpiricValue(this.empRequirement);
	}
	
	@Override
	public Class getInterceptable() {
		// TODO Auto-generated method stub
		return UpgradeLife.class;
	}
	
	@Override
	public UpgradeLife getOwner() {
		// TODO Auto-generated method stub
		return (UpgradeLife) super.getOwner();
	}

}
