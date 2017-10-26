package org.cx.game.action;

import org.cx.game.card.LifeCard;

public interface IUpgradeLife extends IUpgrade {

	public Integer getEmpiricValue();
	
	public void setEmpiricValue(Integer empiricValue);
	
	public void addToEmpiricValue(Integer empiricValue);
	
	@Override
	public LifeCard getOwner();
}
