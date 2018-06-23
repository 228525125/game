package org.cx.game.widget.building;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.action.Upgrade;
import org.cx.game.action.IAction;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.rule.IRule;
import org.cx.game.tools.CommonIdentifier;
import org.cx.game.tools.PropertiesUtil;
import org.cx.game.tools.Util;
import org.cx.game.widget.building.IBuilding;
import org.cx.game.widget.treasure.IResource;
import org.cx.game.widget.treasure.Resource;

public class UpgradeBuilding extends Upgrade implements IAction {
	
	public UpgradeBuilding(Map<Integer, String> requirement) {
		super(requirement);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void action(Object... objects) {
		// TODO Auto-generated method stub
		super.action(objects);
		
		getOwner().setStatus(IBuilding.Building_Status_Complete);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("position", getOwner().getPlace().getPosition());
		map.put("building", this);
		map.put("level", getLevel());
		NotifyInfo info = new NotifyInfo(CommonIdentifier.Building_Upgrade,map);
		super.notifyObservers(info);
	}
	
	@Override
	public IBuilding getOwner() {
		// TODO Auto-generated method stub
		return (IBuilding) super.getOwner();
	}
}
