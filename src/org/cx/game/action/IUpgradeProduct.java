package org.cx.game.action;

import org.cx.game.action.IUpgrade;
import org.cx.game.widget.building.IProduct;

public interface IUpgradeProduct extends IUpgrade {

	public IProduct getOwner();
}
