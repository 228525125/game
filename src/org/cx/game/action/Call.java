package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.ActiveSkill;
import org.cx.game.card.skill.ISkill;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.JsonOut;
import org.cx.game.rule.CallRule;
import org.cx.game.rule.IRule;
import org.cx.game.widget.IContainer;
import org.cx.game.widget.IControlQueue;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IPlace;

public class Call extends Action implements ICall {
	
	private Integer consume = 1;
	
	public Call() {
		// TODO Auto-generated constructor stub
		addObserver(new CallRule(this));;
	}
	
	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return (LifeCard) super.getOwner();
	}
	
	@Override
	public void action(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		super.action(objects);
		
		IPlace place = (IPlace) objects[0];
		
		IPlayer player = getOwner().getPlayer();
		player.getUseCard().remove(getOwner());      //出牌
		
		/* 召唤的动作应在place_in之前，因为place_in动作与移动时的place_in动作相同 */
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getPlayer());
		map.put("container", getOwner().getContainer());
		map.put("card", getOwner());
		map.put("position", place.getPosition());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Action_Call,map);
		super.notifyObservers(info);           //通知所有卡片对象，召唤事件
		
		IContainer ground = place.getContainer();
		ground.add(place.getPosition(), getOwner());
		
		IControlQueue cq = player.getContext().getQueue();
		cq.add(getOwner());   //插入队列
	}

	@Override
	public Integer getConsume() {
		// TODO Auto-generated method stub
		return consume;
	}
	
	public void setConsume(Integer consume) {
		this.consume = consume;
	}
}
