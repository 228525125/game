package org.cx.game.action;

import org.cx.game.action.IUpgrade;
import org.cx.game.widget.building.IBuilding;

public interface IUpgradeBuilding extends IUpgrade {

	public IBuilding getOwner();
}
