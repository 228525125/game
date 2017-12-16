package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.action.IUpgrade;
import org.cx.game.action.Upgrade;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.widget.building.IProduct;

public class UpgradeProduct extends Upgrade implements IUpgradeProduct {
	
	@Override
	public void updateRequirement() {
		// TODO Auto-generated method stub
		Integer riseRatio = getLevel()>1 ? IUpgrade.DefaultProductRiseRatio*getLevel() : 100;
		for(String key : getRequirement().keySet()){
			Integer value = getRequirement().get(key);
			value = value * riseRatio / 100;
			getRequirement().put(key, value);
		}
	}
	
	@Override
	public Map<String, Integer> getRequirement() {
		// TODO Auto-generated method stub
		if(super.getRequirement().isEmpty())
			super.getRequirement().put(IPlayer.Gold, DefaultProductUpgradeGoldRequirement);
		return super.getRequirement();
	}
	
	@Override
	public void action(Object... objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		super.action(objects);
		
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
	
	@Override
	public IProduct getOwner() {
		// TODO Auto-generated method stub
		return (IProduct) super.getOwner();
	}
}
