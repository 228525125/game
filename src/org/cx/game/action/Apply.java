package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.card.ICard;
import org.cx.game.card.MagicCard;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.JsonOut;
import org.cx.game.tools.Logger;

public class Apply extends Action implements IApply {

	private Integer consume = 1;
	
	public Apply() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	@Override
	public MagicCard getOwner() {
		// TODO Auto-generated method stub
		return (MagicCard) super.getOwner();
	}
	
	@Override
	public void action(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		super.action(objects);
		
		IPlayer player = getOwner().getPlayer();
		Integer power = player.getResource();
		if(power>=consume){
			player.setResource(power-consume);
			getOwner().initState();
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", getOwner().getPlayer());
			map.put("container", getOwner().getContainer());
			map.put("card", getOwner());
			map.put("position", getOwner().getContainerPosition());
			NotifyInfo info = new NotifyInfo(NotifyInfo.Card_MagicCard_Apply,map);
			super.notifyObservers(info);           //通知所有卡片对象，攻击事件
		}else{
			Logger.logger.debug("能量不足，法术失败！");
		}
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
