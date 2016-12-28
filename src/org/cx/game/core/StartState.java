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
		NotifyInfo info = new NotifyInfo(NotifyInfo.Context_Start,map);
		super.notifyObservers(info);
		
		IPlayer player1 = context.getPlayer1();
		IPlayer player2 = context.getPlayer2();
		LifeCard hero1 = player1.getHeroCard();
		LifeCard hero2 = player2.getHeroCard();
		ICardGroup cardGroup1 = player1.getCardGroup();
		ICardGroup cardGroup2 = player2.getCardGroup();
		cardGroup1.out(cardGroup1.getPosition(hero1));
		cardGroup2.out(cardGroup2.getPosition(hero2));
		IUseCard useCard1 = player1.getUseCard();
		IUseCard useCard2 = player2.getUseCard();
		useCard1.add(useCard1.getSize(), hero1);
		useCard2.add(useCard2.getSize(), hero2);
		
		/*
		 * 游戏开始各抽5张牌
		 */
		for(int i=0;i<5;i++){
			ICard card1 = cardGroup1.out();
			if(null!=card1){
		    	useCard1.add(useCard1.getSize(),card1);
			}
			
			ICard card2 = cardGroup2.out();
	    	if(null!=card2){
		    	useCard2.add(useCard2.getSize(),card2);
	    	}
		}
		
		/*
		 * 英雄登场
		 */
		
		IGround ground = player1.getGround();
		IPlace place1 = ground.getPlace(ground.getCampPosition(player1));
		IPlace place2 = ground.getPlace(ground.getCampPosition(player2));
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
