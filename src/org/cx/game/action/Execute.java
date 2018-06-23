package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.core.IPlayer;
import org.cx.game.corps.AbstractCorps;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.tools.CommonIdentifier;
import org.cx.game.widget.building.IBuilding;
import org.cx.game.widget.building.IOption;
import org.cx.game.widget.building.OptionExecuteProcess;
import org.cx.game.widget.building.OptionSpacingProcess;
import org.cx.game.widget.building.AbstractProcess;

public class Execute extends AbstractAction implements IAction {
	
	public IOption getOwner() {
		// TODO Auto-generated method stub
		return (IOption) super.getOwner();
	}
	
	@Override
	public void action(Object... objects) {
		// TODO Auto-generated method stub
		
		Integer position = null;
		
		IBuilding building = getOwner().getOwner();
		position = building.getPlace().getPosition();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("building", building);
		map.put("option", getOwner());
		map.put("position", position);
		NotifyInfo info = new NotifyInfo(CommonIdentifier.Building_Option_Execute,map);
		super.notifyObservers(info);
		
		/*
		 * 执行选项间隔周期
		 */
		getOwner().cooling();
	}

}
