package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.card.ICard;
import org.cx.game.card.TrickCard;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.JsonOut;
import org.cx.game.widget.IPlace;

public class Setup extends Action implements ISetup {

	public Setup() {
		// TODO Auto-generated constructor stub
		super();
		setParameterTypeValidator(new Class[]{IPlace.class});
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
		
		IPlace place = (IPlace) objects[0];
		getOwner().initState();
		
		IPlayer player = getOwner().getPlayer();
		player.getUseCard().remove(getOwner());      //出牌
		place.addTrick(getOwner());
			
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", player);
		map.put("container", getOwner().getContainer());
		map.put("card", getOwner());
		map.put("position", place.getPosition());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_TrickCard_Setup,map);
		super.notifyObservers(info);           //通知所有卡片对象，召唤事件
	}

}
