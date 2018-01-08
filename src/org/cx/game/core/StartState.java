package org.cx.game.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.cx.game.corps.CorpsFactory;
import org.cx.game.corps.Corps;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.widget.GroundFactory;
import org.cx.game.widget.IGround;
import org.cx.game.widget.building.IBuilding;
import org.cx.game.widget.building.IOption;
import org.cx.game.widget.building.OptionRevive;
import org.cx.game.widget.treasure.ITreasure;

public class StartState extends PlayState {
	
	@Override
	public void deploy() throws RuleValidatorException {
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
	public void start() throws RuleValidatorException {
		// TODO Auto-generated method stub
		provision();
		
		context.switchControl();
		
		deploy();		
	}
	
	/**
	 * 游戏开始前的准备工作
	 */
	private void provision(){
		Map<String,Object> map = new HashMap<String,Object>();
		IGround ground = context.getGround();
		Map<Integer, Integer> landformMap = ground.getLandformMap();
		Map<String, Integer> landform = new HashMap<String, Integer>();
		for(Integer i : landformMap.keySet())
			landform.put(i.toString(), landformMap.get(i));
		
		Map<String, ITreasure> treasureMap = new HashMap<String, ITreasure>();
		for(Integer i : ground.getTreasureMap().keySet())
			treasureMap.put(i.toString(), ground.getTreasureMap().get(i));
		
		map.put("landform", landform);
		map.put("buildingList", ground.getBuildingList());
		map.put("treasure", treasureMap);
		NotifyInfo info = new NotifyInfo(NotifyInfo.Context_Start,map);
		super.notifyObservers(info);
		
		/*
		 * 英雄登场
		 
		for(IPlayer player : context.getPlayerList()){
			Corps hero = player.getHero();
			if(null!=hero){
				IPlace place = ground.getPlace(player.getHeroEntry());
				try {
					hero.call(place);
				} catch (RuleValidatorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}*/
		
		/*
		 * 登记英雄
		 */
		for(IPlayer player : context.getPlayerList()){
			for(Integer heroID : player.getHeroIDList()){
				Corps hero = (Corps) CorpsFactory.getInstance(heroID, player);
				IBuilding town = ground.getBuilding(player.getHomePosition());
				IOption reviveOption = new OptionRevive(hero);
				reviveOption.setOwner(town);
				town.getOptions().add(reviveOption);
				
				player.addHero(hero);
			}
		}
		
		
		/*
		 * 控制player的hero被激活
		 
		Corps hero = context.getControlPlayer().getHero();
		if(null!=hero){
			Integer speed = hero.getActivate().getSpeed();
			hero.getActivate().addToVigour(speed);
			try {
				hero.activate(true);
			} catch (RuleValidatorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
	}
}
