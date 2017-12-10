package org.cx.game.action;

import java.util.Map;

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
	public Map<String,Integer> getRequirement() {
		// TODO Auto-generated method stub
		return this.upgrade.getRequirement();
	}
	
	@Override
	public void setRequirement(Map<String,Integer> consume) {
		// TODO Auto-generated method stub
		this.upgrade.setRequirement(consume);
	}
	
	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return this.upgrade.getOwner();
	}

}
