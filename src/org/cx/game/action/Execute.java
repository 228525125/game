package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.rule.ExecuteRule;
import org.cx.game.widget.building.IOption;

public class Execute extends Action implements IExecute {

	public Execute() {
		// TODO Auto-generated constructor stub
		addObserver(new ExecuteRule(this));
	}
	
	public IOption getOwner() {
		// TODO Auto-generated method stub
		return (IOption) super.getOwner();
	}
	
	@Override
	public void action(Object... objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getOwner().getPlayer());
		map.put("container", getOwner().getOwner().getOwner().getContainer());
		map.put("building", getOwner().getOwner());
		map.put("option", getOwner());
		map.put("position", getOwner().getOwner().getPosition());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Building_Option_Execute,map);
		super.notifyObservers(info);
	}

}
