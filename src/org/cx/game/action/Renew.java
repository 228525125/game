package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.widget.IGround;
import org.cx.game.widget.Place;

public class Renew extends Action implements IRenew {
	
	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return (LifeCard) super.getOwner();
	}
	
	@Override
	public void action(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		
		Place place = (Place) objects[0];
		
		IGround ground = place.getOwner();
		ground.add(place.getPosition(), getOwner());
						
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("card", getOwner());
		map.put("position", place.getPosition());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Action_Renew,map);
		super.notifyObservers(info);           //通知所有卡片对象，召唤事件
		
		getOwner().getDeath().setStatus(Death.Status_Live);
		getOwner().getAttacked().setFightBack(true);
	}
}
