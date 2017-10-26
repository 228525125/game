package org.cx.game.action;

import org.cx.game.card.LifeCard;
import org.cx.game.intercepter.ProxyFactory;

public class UpgradeLifeDecorator extends UpgradeDecorator implements
		IUpgradeLife {

	private IUpgradeLife upgrade = null;
	
	public UpgradeLifeDecorator(IUpgradeLife upgrade) {
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
		Object proxy = ProxyFactory.getProxy(this.upgrade);     
		((IUpgradeLife)proxy).addToEmpiricValue(empiricValue);
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
	public Integer getStandard() {
		// TODO Auto-generated method stub
		return this.upgrade.getStandard();
	}
	
	@Override
	public void setStandard(Integer consume) {
		// TODO Auto-generated method stub
		this.upgrade.setStandard(consume);
	}
	
	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return this.upgrade.getOwner();
	}

}
