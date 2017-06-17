package org.cx.game.core;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.JsonOut;
import org.cx.game.widget.ControlQueue;
import org.cx.game.widget.ICardGroup;
import org.cx.game.widget.IControlQueue;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IPlace;
import org.cx.game.widget.IUseCard;

public class StartState extends PlayState {

	public StartState() {
		// TODO Auto-generated constructor stub
		addObserver(new JsonOut());
	}
	
	@Override
	public void deploy() {
		// TODO Auto-generated method stub
		context.setPlayState(IContext.deployState);
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
		
		Map<Integer, Integer> landformMap = context.getPlayer1().getGround().getLandformMap();
		Map<String, Integer> landform = new HashMap<String, Integer>();
		for(Integer i : landformMap.keySet())
			landform.put(i.toString(), landformMap.get(i));
		
		map.put("landform", landform);
		map.put("buildingList", context.getPlayer1().getGround().getBuildingList());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Context_Start,map);
		super.notifyObservers(info);
		
		IPlayer player1 = context.getPlayer1();
		IPlayer player2 = context.getPlayer2();
		LifeCard hero1 = player1.getHero();
		LifeCard hero2 = player2.getHero();
		
		/*
		 * 英雄登场
		 */
		IGround ground = player1.getGround();
		IPlace place1 = ground.getPlace(player1.getHeroEntry());
		IPlace place2 = ground.getPlace(player2.getHeroEntry());
		try {
			hero1.call(place1);
			hero2.call(place2);
		} catch (RuleValidatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		context.switchControl();
		
		deploy();
	}
}
