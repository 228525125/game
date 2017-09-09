package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.card.LifeCard;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.rule.ExecuteRule;
import org.cx.game.widget.building.IBuilding;
import org.cx.game.widget.building.IOption;

public class Execute extends Action implements IExecute {
	
	public IOption getOwner() {
		// TODO Auto-generated method stub
		return (IOption) super.getOwner();
	}
	
	@Override
	public void action(Object... objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		IPlayer player = null;
		Integer position = null;
		
		IBuilding building = getOwner().getOwner();
		player = building.getPlayer();
		position = building.getPosition();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", player);
		map.put("container", player.getGround());
		map.put("building", building);
		map.put("option", getOwner());
		map.put("position", position);
		NotifyInfo info = new NotifyInfo(NotifyInfo.Building_Option_Execute,map);
		super.notifyObservers(info);
	}

}
