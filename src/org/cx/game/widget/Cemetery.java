package org.cx.game.widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.JsonOut;

public class Cemetery extends Observable implements ICemetery {
	
	private Map<String,List<IIntercepter>> intercepterList = new HashMap<String,List<IIntercepter>>();
	protected List<LifeCard> lifeList = new ArrayList<LifeCard>();
	
	private IPlace place;
	
	public Cemetery(IPlace place) {
		// TODO Auto-generated constructor stub
		super();
		this.place = place;
	}
	
	
	/**
	 * 进入墓地
	 */
	public void add(LifeCard life) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", life.getPlayer());
		map.put("container", life.getPlayer().getContext().getGround());
		map.put("life", life);
		map.put("position", place.getPosition());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Container_Ground_Place_Cemetery_Add,map);
		notifyObservers(info);    //通知观察者

		lifeList.add(life); 
		getOwner().setEmpty(true);
	}
	
	/**
	 * 移出墓地的卡片
	 * @param card
	 */
	public void remove(LifeCard life){				
		lifeList.remove(life);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", life.getPlayer());
		map.put("container", life.getPlayer().getContext().getGround());
		map.put("life", life);
		map.put("position", place.getPosition());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Container_Ground_Place_Cemetery_Remove,map);
		notifyObservers(info);    //通知观察者
	}
	
	@Override
	public IPlace getOwner() {
		// TODO Auto-generated method stub
		return place;
	}
	
	
	@Override
	public Integer indexOf(LifeCard life) {
		// TODO Auto-generated method stub
		return lifeList.indexOf(life);
	}
	
	@Override
	public Boolean contains(LifeCard life) {
		// TODO Auto-generated method stub
		return lifeList.contains(life);
	}
	

	@Override
	public Integer getSize() {
		// TODO Auto-generated method stub
		return lifeList.size();
	}


	@Override
	public LifeCard getLife(Integer index) {
		// TODO Auto-generated method stub
		if(index<getSize())
			return lifeList.get(index);
		else
			return null;
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
		/*List<IIntercepter> list = intercepterList.get(intercepter.getIntercepterMethod());
		if(null!=list){
			list.remove(intercepter);
		}*/
		
		intercepter.delete();
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

	public List<LifeCard> getList() {
		return lifeList;
	}
} 
