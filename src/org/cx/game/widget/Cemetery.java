package org.cx.game.widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.cx.game.corps.AbstractCorps;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.observer.NotifyInfo;

/**
 * 墓地，是基于ground每个格子都带一个墓地
 * @author chenxian
 *
 */
public class Cemetery {
	
	private List<AbstractCorps> corpsList = new ArrayList<AbstractCorps>();
	
	private AbstractPlace place;
	
	public Cemetery(AbstractPlace place) {
		// TODO Auto-generated constructor stub
		super();
		this.place = place;
	}
	
	
	/**
	 * 进入墓地
	 */
	void add(AbstractCorps corps) {
		// TODO Auto-generated method stub
		corpsList.add(corps); 
	}
	
	/**
	 * 移出墓地
	 * @param corps
	 */
	void remove(AbstractCorps corps){				
		corpsList.remove(corps);
	}
	
	public AbstractPlace getOwner() {
		// TODO Auto-generated method stub
		return place;
	}
	
	
	/**
	 * 因为Cemetery的getPosition(corps)返回Cemetery所在ground的position
	 * @param corps
	 * @return
	 */
	public Integer indexOf(AbstractCorps corps) {
		// TODO Auto-generated method stub
		return corpsList.indexOf(corps);
	}
	
	public Boolean contains(AbstractCorps corps) {
		// TODO Auto-generated method stub
		return corpsList.contains(corps);
	}
	
	public Integer getSize() {
		// TODO Auto-generated method stub
		return corpsList.size();
	}

	public AbstractCorps getCorps(Integer index) {
		// TODO Auto-generated method stub
		if(index<getSize())
			return corpsList.get(index);
		else
			return null;
	}

	public List<AbstractCorps> getList() {
		return corpsList;
	}
} 
