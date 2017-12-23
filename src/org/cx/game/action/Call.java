package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.widget.IContainer;
import org.cx.game.widget.IPlace;

public class Call extends Action implements ICall {
	
	private Map<String,Integer> consume = new HashMap<String,Integer>();
	private Integer ration = 1;
	
	private Integer nop = 1;           //人数 
	
	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return (LifeCard) super.getOwner();
	}

	@Override
	public Map<String,Integer> getConsume() {
		// TODO Auto-generated method stub
		return consume;
	}
	
	public void setConsume(Map<String,Integer> consume) {
		for(String resType : consume.keySet())
			getConsume().put(resType, consume.get(resType));
	}
	
	@Override
	public Integer getRation() {
		// TODO Auto-generated method stub
		return this.ration;
	}
	
	@Override
	public void setRation(Integer ration) {
		// TODO Auto-generated method stub
		if(!ration.equals(this.ration)){
			this.ration = ration;
		}
	}
	
	public Integer getNop() {
		return nop;
	}

	public void setNop(Integer nop) {
		if(!nop.equals(this.nop)){
			this.nop = nop;
			
			getOwner().getAttack().updateDmg();
		}
	}
	
	@Override
	public void action(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub

		IPlace place = (IPlace) objects[0];
		Integer nop = (Integer) objects[1];
		
		/*
		 * 招募人数
		 */
		setNop(nop);
		
		/* 招募的动作应在place_in之前，因为place_in动作与移动时的place_in动作相同 */
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getPlayer());
		map.put("container", place.getContainer());
		map.put("card", getOwner());
		map.put("position", place.getPosition());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Action_Call,map);
		super.notifyObservers(info);           //通知所有卡片对象，召唤事件
		
		/*
		 * 加入战场
		 * 因为顺序问题，没有写在Rule中
		 */
		IContainer ground = place.getContainer();
		ground.add(place.getPosition(), getOwner());
		
		/*
		 * 刚招募的部队允许反击
		 */
		getOwner().getDeath().setStatus(IDeath.Status_Live);
		getOwner().getAttacked().setFightBack(true);
	}

}
