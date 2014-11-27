package org.cx.game.widget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.cx.game.card.LifeCard;
import org.cx.game.core.IPlayer;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.IntercepterAscComparator;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.JsonOut;

public class ControlQueue extends Observable implements IControlQueue {

	private Map<String,List<IIntercepter>> intercepterList = new HashMap<String,List<IIntercepter>>();
	private List<Place> queue1 = new ArrayList<Place>();
	private List<Place> queue2 = new ArrayList<Place>();
	private Map<Integer,List<Place>> map = new HashMap<Integer,List<Place>>(); 
	private List<Place> queue = null;        //当前队列
	private Integer curQueue = 0;	
	
	public ControlQueue() {
		// TODO Auto-generated constructor stub
		map.put(1, queue1);
		map.put(2, queue2);
		this.queue = this.queue1;
		this.curQueue = 1;
		
		addObserver(new JsonOut());
	}
	
	@Override
	public Object out() {
		// TODO Auto-generated method stub
		Object object = null;
		
		if(queue.isEmpty())             //如果当前queue为空，则切换到下个queue，并进行递归调用
			swapQueue();
		
		Place place = queue.get(0);  //返回最前面的一个place
		remove(place.getObject());
		
		place.setCount(place.getCount() - consume); //swapQueue方法确保当前queue内的place始终大于消耗，因此这里不用判断，直接 减掉消耗，并获得一次控制机会
		object = place.getObject();
		if(consume<=place.getCount())        //如果减掉消耗后仍大于consume，则继续插入当前queue进行排序
			insert(place);
		else
			insertOtherQueue(place); //如果小于额定消耗，则进入下一个queue
		
		return object; 
	}

	@Override
	public void insert(Object object) {
		// TODO Auto-generated method stub
		Place place = new Place(object);
		queue.add(place);
		Collections.sort(queue, new PlaceComparator());
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("unit", place.getObject());
		map.put("position", indexOf(place));
		map.put("queue", toListMap());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Context_ControlQueue_Insert,map);
		notifyObservers(info);
	}
	
	@Override
	public void remove(Object object) {
		// TODO Auto-generated method stub
		Place place = new Place(object);
		Integer position = this.queue1.indexOf(place);
		if(0<=position){
			this.queue1.remove(place);
		}else{
			position = this.queue2.indexOf(place);
			this.queue2.remove(place);
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("unit", place.getObject());
		map.put("position", position);
		map.put("queue", toListMap());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Context_ControlQueue_Remove,map);
		notifyObservers(info);
	}
	
	@Override
	public void refurbish() {
		// TODO Auto-generated method stub
		for(int i=0;i<queue.size();i++)
			queue.get(i).loadSpeed();
		Collections.sort(queue, new PlaceComparator());
		
		List<Place> other = otherQueue(queue);
		for(int i=0;i<other.size();i++)
			other.get(i).loadSpeed();
		Collections.sort(other, new PlaceComparator());
	}
	
	private void swapQueue(){
		if(1==this.curQueue){
			this.queue = map.get(2);
			this.curQueue = 2;
		}else{
			this.queue = map.get(1);
			this.curQueue = 1;
		}
		
		for(Place place : this.queue){
			if(consume>place.getCount()){
				remove(place.getObject());
				insertOtherQueue(place);
			}
		}
		
		if(queue.isEmpty())       //递归，防止交换后queue中的place的count都不满足consume，应该不会有这种情况发生
			swapQueue();
	}
	
	private void insertOtherQueue(Place place){
		Integer count = place.getCount();
		Integer consume = 100;        
		Integer speed = 0;
		if (place.getObject() instanceof IPlayer) {
			speed = consume;
		}
		if (place.getObject() instanceof LifeCard) {
			LifeCard life = (LifeCard) place.getObject();
			speed = life.getAttack().getSpeedChance();
		}
		
		place.setCount(count + speed);          //当加入下一个队列时，表示下一个回合，所以要增加一次活力
		
		List<Place> list = otherQueue(queue);
		list.add(place);
		Collections.sort(list, new PlaceComparator());   //插入增加活力的place，需要重新排序
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("unit", place.getObject());
		map.put("position", indexOf(place));
		map.put("queue", toListMap());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Context_ControlQueue_Insert,map);
		notifyObservers(info);
	}
	
	private Integer indexOf(Place place){
		Integer index = queue.indexOf(place);
		
		if(-1==index){
			List<Place> list = otherQueue(queue);
			index = list.indexOf(place);
			index += queue.size();
		}
		
		return index;
	}
	
	private List<Place> otherQueue(List<Place> queue){
		List<Place> list = null;
		if(1==this.curQueue)
			list = map.get(2);
		else
			list = map.get(1);
		return list;
	}
	
	private List<Map<String, Object>> toListMap(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		for(int i=0;i<this.queue.size();i++){
			Map<String, Object> map = new HashMap<String, Object>();
			Place place = this.queue.get(i);
			map.put("position",indexOf(place));
			map.put("unit", place.getObject());
			list.add(map);
		}
		
		for(int i=0;i<otherQueue(this.queue).size();i++){
			Map<String, Object> map = new HashMap<String, Object>();
			Place place  = otherQueue(this.queue).get(i);
			map.put("position",indexOf(place));
			map.put("unit", place.getObject());
			list.add(map);
		}
		return list;
	}
	
	@Override
	public void notifyObservers(Object arg) {
		// TODO Auto-generated method stub
		super.setChanged();
		super.notifyObservers(arg);
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
	
	class Place {
		private Object obj = null;
		private Integer count = 0;
		
		public Place(Object object) {
			// TODO Auto-generated constructor stub
			this.obj = object;
			loadSpeed();
		}
		
		public void loadSpeed(){
			if (obj instanceof LifeCard) {
				LifeCard life = (LifeCard) obj;
				this.count = life.getAttack().getSpeedChance();
			}
			if (obj instanceof IPlayer) {
				this.count = 100;
			}
		}
		
		public Integer getCount(){
			return count;
		}
		
		public void setCount(Integer count){
			this.count = count;
		}
		
		public Object getObject(){
			return obj;
		}
		
		@Override
		public boolean equals(Object arg0) {
			// TODO Auto-generated method stub
			if (arg0 instanceof Place) {
				Place place = (Place) arg0;
				return obj.equals(place.getObject());
			}else
				return false;
		}
	}
	
	class PlaceComparator implements Comparator<Place> {

		@Override
		public int compare(Place p1, Place p2) {
			// TODO Auto-generated method stub
			if(p1.getCount()<p2.getCount())
				return 1;
			else if(p1.getCount()==p2.getCount())
				return 0;
			else
				return -1;
		}
	} 
}
