package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.action.IUpgrade;
import org.cx.game.action.Upgrade;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.rule.IRule;
import org.cx.game.widget.building.IBuilding;
import org.cx.game.widget.treasure.IResource;

public class UpgradeBuilding extends Upgrade implements IUpgradeBuilding {
	
	@Override
	public void updateRequirement() {
		// TODO Auto-generated method stub
		//
	}
	
	@Override
	public IResource getRequirement() {
		// TODO Auto-generated method stub
		return getOwner().getConsume();
	}
	
	@Override
	public void setRequirement(IResource requirement) {
		// TODO Auto-generated method stub
		super.setRequirement(requirement);
	}
	
	@Override
	public void action(Object... objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		
		Integer level = getLevel();
		level += 1;
		setLevel(level);
		
		getOwner().setStatus(IBuilding.Building_Status_Complete);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getPlayer());
		map.put("container", getOwner().getPlayer().getContext().getGround());
		map.put("position", getOwner().getPosition());
		map.put("building", this);
		map.put("level", getLevel());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Building_Action_Upgrade,map);
		super.notifyObservers(info);
	}
	
	@Override
	public IBuilding getOwner() {
		// TODO Auto-generated method stub
		return (IBuilding) super.getOwner();
	}

}
