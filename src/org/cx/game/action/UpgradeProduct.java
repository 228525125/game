package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.action.IUpgrade;
import org.cx.game.action.Upgrade;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.widget.building.IBuilding;
import org.cx.game.widget.building.IProduct;
import org.cx.game.widget.treasure.IResource;

public class UpgradeProduct extends Upgrade implements IUpgradeProduct {
	
	@Override
	public void updateRequirement() {
		// TODO Auto-generated method stub
		//
	}
	
	@Override
	public IResource getRequirement() {
		// TODO Auto-generated method stub
		return super.getRequirement();
	}
	
	@Override
	public IProduct getOwner() {
		// TODO Auto-generated method stub
		return (IProduct) super.getOwner();
	}
	
	@Override
	public void action(Object... objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		
		Integer level = getLevel();
		level += 1;
		setLevel(level);
			
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getOwner().getPlayer());
		map.put("container", getOwner().getOwner().getPlayer().getContext().getGround());
		map.put("position", getOwner().getOwner().getPosition());
		map.put("building", getOwner().getOwner());
		map.put("productType", getOwner().getType());
		map.put("level", getLevel());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Building_Action_Upgrade_Product,map);
		super.notifyObservers(info);
	}
}
