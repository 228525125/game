package org.cx.game.rule;

import org.cx.game.action.IUpgrade;
import org.cx.game.core.IPlayer;
import org.cx.game.widget.building.IBuilding;
import org.cx.game.widget.building.IProduct;
import org.cx.game.widget.building.ProductUpgrade;

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
			player.addToResource(-upgrade.getConsume());
		}
	}
	
	@Override
	public Class getInterceptable() {
		// TODO Auto-generated method stub
		return IUpgrade.class;
	}
	
	@Override
	public IUpgrade getOwner() {
		// TODO Auto-generated method stub
		return (IUpgrade) super.getOwner();
	}

}
