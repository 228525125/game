package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.card.ICard;
import org.cx.game.card.TrickCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.JsonOut;
import org.cx.game.rule.IRule;

/**
 * 暂未使用
 * @author chenxian
 *
 */
public class Trigger extends Action implements ITrigger {
	
	public Trigger() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	@Override
	public TrickCard getOwner() {
		// TODO Auto-generated method stub
		return (TrickCard) super.getOwner();
	}
	
	@Override
	public void action(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		super.action(objects);
		
		getOwner().setDisplay(true);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getPlayer());
		map.put("container", getOwner().getContainer());
		map.put("card", getOwner());
		map.put("position", getOwner().getContainerPosition());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_TrickCard_Trigger,map);
		super.notifyObservers(info);           //通知所有卡片对象，攻击事件
	}
}
