package org.cx.game.rule;

import java.util.Map;

import org.cx.game.action.IUpgrade;
import org.cx.game.action.IUpgradeBuilding;
import org.cx.game.action.UpgradeBuilding;
import org.cx.game.core.IPlayer;
import org.cx.game.widget.building.IBuilding;

public class UpgradeBuildingRule extends Rule implements IRule {

	@Override
	public String getIntercepterMethod() {
		// TODO Auto-generated method stub
		return "action";
	}
	
	@Override
	public void after(Object[] args) {
		// TODO Auto-generated method stub
		IUpgrade upgrade = getOwner();
		
		IBuilding building = (IBuilding) upgrade.getOwner();
		IPlayer player = building.getPlayer();
		if(null!=player){
			Map<String,Integer> req = upgrade.getRequirement();
			player.addToResource(req);
		}
	}
	
	@Override
	public Class getInterceptable() {
		// TODO Auto-generated method stub
		return UpgradeBuilding.class;
	}
	
	@Override
	public UpgradeBuilding getOwner() {
		// TODO Auto-generated method stub
		return (UpgradeBuilding) super.getOwner();
	}

}
