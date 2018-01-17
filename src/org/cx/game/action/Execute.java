package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.core.IPlayer;
import org.cx.game.corps.AbstractCorps;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.widget.building.IBuilding;
import org.cx.game.widget.building.IOption;
import org.cx.game.widget.building.ProcessOptionExecute;
import org.cx.game.widget.building.ProcessOptionSpacing;
import org.cx.game.widget.building.AbstractProcess;

public class Execute extends AbstractAction implements IExecute {
	
	public IOption getOwner() {
		// TODO Auto-generated method stub
		return (IOption) super.getOwner();
	}
	
	@Override
	public void action(Object... objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		
		Integer position = null;
		
		IBuilding building = getOwner().getOwner();
		position = building.getPosition();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("building", building);
		map.put("option", getOwner());
		map.put("position", position);
		NotifyInfo info = new NotifyInfo(NotifyInfo.Building_Option_Execute,map);
		super.notifyObservers(info);
		
		/*
		 * 执行选项间隔周期
		 */
		getOwner().cooling();
	}

}
