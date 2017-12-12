package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.widget.treasure.ITreasure;

public class Picked extends Action implements IPicked {

	@Override
	public ITreasure getOwner() {
		// TODO Auto-generated method stub
		return (ITreasure) super.getOwner();
	}
	
	@Override
	public void action(Object... objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		super.action(objects);
		
		LifeCard life = (LifeCard) objects[0];
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", life.getPlayer());
		map.put("container", life.getContainer());
		map.put("card", life);
		map.put("treasure", getOwner());
		map.put("position", getOwner().getPosition());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Treasure_Action_Picked,map);
		super.notifyObservers(info);
	}
}
