package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.widget.treasure.ITreasure;
import org.cx.game.widget.treasure.Treasure;

public class Pick extends Action implements IPick {

	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return (LifeCard) super.getOwner();
	}
	
	@Override
	public void action(Object... objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		
		ITreasure treasure = (ITreasure) objects[0];
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getPlayer());
		map.put("container", getOwner().getContainer());
		map.put("card", getOwner());
		map.put("treasure", treasure);
		map.put("position", getOwner().getPosition());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Action_Pick,map);
		super.notifyObservers(info);
		
		getOwner().getMove().addToEnergy(-1);
		
		treasure.picked(getOwner());
	}
}
