package org.cx.game.action;
import java.util.HashMap;
import java.util.Map;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.JsonOut;
import org.cx.game.rule.IRule;
import org.cx.game.tools.Logger;
import org.cx.game.widget.ICardGroup;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IPlace;
import org.cx.game.widget.IUseCard;

/**
 * 交换，交换的条件判断应该子类中实现，即必须要有子类（暂未使用）
 * @author chenxian
 *
 */
public class Swap extends Action implements ISwap {
	
	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return (LifeCard) super.getOwner();
	}

	@Override
	public void action(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		super.action(objects);
		
		LifeCard swaped = (LifeCard) objects[0];		
		
		Map<String,Object> entry = new HashMap<String,Object>();
		entry.put("player", getOwner().getPlayer());
		entry.put("swap", getOwner());
		entry.put("swaped", swaped);
		entry.put("container", swaped.getContainer());
		entry.put("position", swaped.getContainerPosition());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Action_Swap,entry);
		super.notifyObservers(info);           //通知所有卡片对象，升级事件
		
		Integer position = swaped.getContainerPosition();
		IGround ground = (IGround) swaped.getContainer();
		IPlace place = ground.getPlace(position);
		
		ground.remove(swaped);
		
		swaped.initState();
		swaped.getDeath().setStatus(IDeath.Status_Exist);
		
		ICardGroup group = getOwner().getPlayer().getCardGroup();
		Integer p = (int)(Math.random()*group.getSize());    //随机插入group
		group.add(p, swaped);
		
		getOwner().call(place);
	}
	
	@Override
	public Integer getConsume(LifeCard swaped) {
		// TODO Auto-generated method stub
		return getOwner().getCall().getConsume()-swaped.getCall().getConsume();
	}
}
