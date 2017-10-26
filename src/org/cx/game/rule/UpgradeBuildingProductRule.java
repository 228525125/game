package org.cx.game.rule;

import org.cx.game.action.IUpgradeProduct;
import org.cx.game.action.IUpgrade;
import org.cx.game.core.IPlayer;
import org.cx.game.widget.building.IBuilding;
import org.cx.game.widget.building.IProduct;

public class UpgradeBuildingProductRule extends Rule implements IRule {

	@Override
	public String getIntercepterMethod() {
		// TODO Auto-generated method stub
		return "action";
	}
	
	@Override
	public void after(Object[] args) {
		// TODO Auto-generated method stub
		IUpgrade upgrade = getOwner();
		
		IProduct product = (IProduct) upgrade.getOwner();
		IBuilding building = product.getOwner();
		IPlayer player = building.getPlayer();
		if(null!=player){
			player.addToResource(-upgrade.getStandard());
		}
	}
	
	@Override
	public Class getInterceptable() {
		// TODO Auto-generated method stub
		return IUpgradeProduct.class;
	}
	
	@Override
	public IUpgradeProduct getOwner() {
		// TODO Auto-generated method stub
		return (IUpgradeProduct) super.getOwner();
	}

}
