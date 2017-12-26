package org.cx.game.action;

import org.cx.game.card.LifeCard;
import org.cx.game.widget.treasure.EmpiricValue;

public interface IUpgradeLife extends IUpgrade {

	public EmpiricValue getEmpiricValue();
	
	public void addToEmpiricValue(Integer empiricValue);
	
	public void addToEmpiricValue(EmpiricValue empiricValue);
	
	@Override
	public LifeCard getOwner();
}
