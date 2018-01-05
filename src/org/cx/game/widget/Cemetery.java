package org.cx.game.widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.cx.game.card.LifeCard;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.observer.NotifyInfo;

/**
 * 墓地，是基于ground每个格子都带一个墓地
 * @author chenxian
 *
 */
public class Cemetery {
	
	private List<LifeCard> lifeList = new ArrayList<LifeCard>();
	
	private Place place;
	
	public Cemetery(Place place) {
		// TODO Auto-generated constructor stub
		super();
		this.place = place;
	}
	
	
	/**
	 * 进入墓地
	 */
	void add(LifeCard life) {
		// TODO Auto-generated method stub
		lifeList.add(life); 
	}
	
	/**
	 * 移出墓地
	 * @param card
	 */
	void remove(LifeCard life){				
		lifeList.remove(life);
	}
	
	public Place getOwner() {
		// TODO Auto-generated method stub
		return place;
	}
	
	
	/**
	 * 因为Cemetery的getPosition(life)返回Cemetery所在ground的position
	 * @param life
	 * @return
	 */
	public Integer indexOf(LifeCard life) {
		// TODO Auto-generated method stub
		return lifeList.indexOf(life);
	}
	
	public Boolean contains(LifeCard life) {
		// TODO Auto-generated method stub
		return lifeList.contains(life);
	}
	
	public Integer getSize() {
		// TODO Auto-generated method stub
		return lifeList.size();
	}

	public LifeCard getLife(Integer index) {
		// TODO Auto-generated method stub
		if(index<getSize())
			return lifeList.get(index);
		else
			return null;
	}

	public List<LifeCard> getList() {
		return lifeList;
	}
} 
