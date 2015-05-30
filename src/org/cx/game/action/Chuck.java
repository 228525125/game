package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.JsonOut;
import org.cx.game.widget.Cemetery;
import org.cx.game.widget.ICemetery;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IUseCard;

public class Chuck extends Action implements IChuck {

	public Chuck() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	@Override
	public void action(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		super.action(objects);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getPlayer());
		map.put("container", getOwner().getContainer());
		map.put("card", getOwner());
		map.put("position", getOwner().getContainerPosition());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Action_Chuck, map);
		super.notifyObservers(info);           //通知所有卡片对象，丢弃事件
		
		IUseCard use = getOwner().getPlayer().getUseCard();
		use.remove(getOwner());
	}

}
