package org.cx.game.action;

import org.cx.game.corps.Corps;
import org.cx.game.widget.treasure.EmpiricValue;

public interface IUpgradeCorps extends IUpgrade {

	public EmpiricValue getEmpiricValue();
	
	public void addToEmpiricValue(Integer empiricValue);
	
	public void addToEmpiricValue(EmpiricValue empiricValue);
	
	@Override
	public Corps getOwner();
}
