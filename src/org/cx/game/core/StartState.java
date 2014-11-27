package org.cx.game.core;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.JsonOut;
import org.cx.game.widget.ControlQueue;
import org.cx.game.widget.ICardGroup;
import org.cx.game.widget.IControlQueue;
import org.cx.game.widget.IUseCard;

public class StartState extends PlayState {

	public StartState() {
		// TODO Auto-generated constructor stub
		addObserver(new JsonOut());
	}
	
	@Override
	public void deploy() {
		// TODO Auto-generated method stub
		context.setPlayState(Context.deployState);
		context.deploy();
	}

	@Override
	public void done() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		//比赛才开始怎么可能结束
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		NotifyInfo info = new NotifyInfo(NotifyInfo.Context_Start,map);
		super.notifyObservers(info);
		
		for(int i=0;i<5;i++){
			IPlayer player = context.getPlayer1();
			ICardGroup cardGroup = player.getCardGroup();
			ICard card = cardGroup.out();
			IUseCard useCard = player.getUseCard();
	    	useCard.add(useCard.getSize(),card);
	    	player = context.getPlayer2();
	    	cardGroup = player.getCardGroup();
	    	card = cardGroup.out();
	    	useCard = player.getUseCard();
	    	useCard.add(useCard.getSize(),card);
		}
		
		context.switchControl();
		context.setPlayState(Context.deployState);
		context.deploy();
	}
}
