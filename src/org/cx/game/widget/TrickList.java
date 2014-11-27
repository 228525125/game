package org.cx.game.widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.card.TrickCard;
import org.cx.game.card.skill.ITrick;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.JsonOut;

public class TrickList extends Observable implements ITrickList {

	private Map<String,List<IIntercepter>> intercepterList = new HashMap<String,List<IIntercepter>>();
	protected List<ITrick> trickList = new ArrayList<ITrick>();
	
	private IPlace place;
	
	public TrickList(IPlace place) {
		// TODO Auto-generated constructor stub
		addObserver(new JsonOut());
		this.place = place;
	}
	
	public void add(ITrick trick){
		trickList.add(trick);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", trick.getPlayer());
		map.put("container", trick.getPlayer().getGround());
		map.put("trick", trick);
		map.put("position", place.getPosition());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Container_TrickList_Add,map);
		notifyObservers(info);    //通知观察者
	}
	
	@Override
	public void remove(ITrick trick){
		trickList.remove(trick);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", trick.getPlayer());
		map.put("container", trick.getPlayer().getGround());
		map.put("trick", trick);
		map.put("position", place.getPosition());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Container_TrickList_Remove,map);
		notifyObservers(info);    //通知观察者
		
	}
	
	@Override
	public Integer indexOf(ITrick trick) {
		// TODO Auto-generated method stub
		return trickList.indexOf(trick);
	}
	
	@Override
	public Integer getSize() {
		// TODO Auto-generated method stub
		return trickList.size();
	}

	@Override
	public ITrick getTrick(Integer index) {
		// TODO Auto-generated method stub
		if(index<getSize())
			return trickList.get(index);
		else
			return null;
	}

	@Override
	public IPlace getPlace() {
		// TODO Auto-generated method stub
		return place;
	}

	@Override
	public Boolean contains(ITrick trick) {
		// TODO Auto-generated method stub
		return trickList.contains(trick);
	}

	@Override
	public void addIntercepter(IIntercepter intercepter) {
		// TODO Auto-generated method stub
		List<IIntercepter> intercepters = intercepterList.get(intercepter.getIntercepterMethod());
		if(null!=intercepters){
			intercepters.add(intercepter);
		}else{
			intercepters = new ArrayList<IIntercepter>();
			intercepters.add(intercepter);
			intercepterList.put(intercepter.getIntercepterMethod(), intercepters);
		}
	}

	@Override
	public void deleteIntercepter(IIntercepter intercepter) {
		// TODO Auto-generated method stub
		List<IIntercepter> list = intercepterList.get(intercepter.getIntercepterMethod());
		if(null!=list){
			list.remove(intercepter);
		}
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		intercepterList.clear();
	}

	@Override
	public Map<String,List<IIntercepter>> getIntercepterList() {
		// TODO Auto-generated method stub
		return intercepterList;
	}
	
	@Override
	public void notifyObservers(Object arg0) {
		// TODO Auto-generated method stub
		super.setChanged();
		super.notifyObservers(arg0);
	}
}
