package org.cx.game.widget.building;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.action.IUpgrade;
import org.cx.game.action.AbstractUpgrade;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.rule.IRule;
import org.cx.game.tools.PropertiesUtil;
import org.cx.game.tools.Util;
import org.cx.game.widget.building.IBuilding;
import org.cx.game.widget.treasure.IResource;
import org.cx.game.widget.treasure.Resource;

public class UpgradeBuilding extends AbstractUpgrade implements IUpgrade {
	
	public UpgradeBuilding(Map<Integer, String> requirement) {
		super(requirement);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void action(Object... objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		
		Integer level = getLevel();
		level += 1;
		setLevel(level);
		
		getOwner().setStatus(IBuilding.Building_Status_Complete);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("position", getOwner().getPosition());
		map.put("building", this);
		map.put("level", getLevel());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Building_Upgrade,map);
		super.notifyObservers(info);
	}
	
	@Override
	public IBuilding getOwner() {
		// TODO Auto-generated method stub
		return (IBuilding) super.getOwner();
	}
}
