package org.cx.game.widget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.cx.game.core.IPlayer;
import org.cx.game.corps.AbstractCorps;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.AbstractResponse;
import org.cx.game.out.ResponseFactory;
import org.cx.game.rule.RuleGroupFactory;

public class ControlQueue extends Observable implements IControlQueue {

	private Map<String,List<IIntercepter>> intercepterList = new HashMap<String,List<IIntercepter>>();
	private List<Place> queueList = new ArrayList<Place>();                // queue1 + queue2
	private List<Place> queue1 = new ArrayList<Place>();                   // 两个队列用于切换，实现按优先级排序
	private List<Place> queue2 = new ArrayList<Place>();
	private Map<Integer,List<Place>> map = new HashMap<Integer,List<Place>>(); 
	private List<Place> queue = null;        //当前队列
	private Integer curQueue = 0;	
	
	private List<Place> priorQueue = new ArrayList<Place>();     //这个队列的对象会优先出场，例如具有突袭效果的随从；
	
	public ControlQueue() {
		// TODO Auto-generated constructor stub
		addObserver(ResponseFactory.getResponse());
		
		map.put(1, queue1);
		map.put(2, queue2);
		this.queue = this.queue1;
		this.curQueue = 1;
	}
	
	@Override
	public void add(IPlayer player) {
		// TODO Auto-generated method stub
		Place place = new Place(player);
		
		/*
		 * 这里为什么直接插入第二队列，它的效果就像你排队办事一样，你总是从最后一个排起
		 */
		place.setCount(0);
		insertOtherQueue(place);
		
		this.queueList.add(place);
	}
	
	public void remove(IPlayer player) {
		// TODO Auto-generated method stub
		Place place = new Place(player);
		takeOut(place);
		
		this.queueList.remove(place);
	}
	
	@Override
	public Place getPlace(IPlayer player) {
		// TODO Auto-generated method stub
		for(Place place : this.queueList){
			if(place.getObject().equals(player))
				return place;
		}
		return null;
	}
	
	@Override
	public Object out() {
		// TODO Auto-generated method stub
		Object object = null;
		Place place = null;
		
		if(priorQueue.isEmpty()){
			if(queue.isEmpty())             //如果当前queue为空，则切换到下个queue，并进行递归调用
				swapQueue();
			
			place = queue.get(0);  //返回最前面的一个place
			takeOut(place);        //从整个队列中取出place
			
			place.setCount(place.getCount() - consume); //swapQueue方法确保当前queue内的place始终大于消耗，因此这里不用判断，直接 减掉消耗，并获得一次控制机会
			object = place.getObject();
		}else{
			place = priorQueue.remove(0);
			object = place.getObject();
			
			place.setCount(0);
		}
		
		if(consume<=place.getCount()){        //如果减去消耗后仍大于consume，则继续插入当前queue进行排序
			insert(place);
		}else{
			insertOtherQueue(place); //如果小于额定消耗，则进入下一个queue
		}
		
		return object;
	}
	
	public void refurbish() {
		// TODO Auto-generated method stub
		for(int i=0;i<queue.size();i++){
			Place place = queue.get(i);
			if(consume>place.getCount()){
				takeOut(place);
				insertOtherQueue(place);
			}
		}
		Collections.sort(queue, new PlaceComparator());
		
		List<Place> other = otherQueue(queue);
		Collections.sort(other, new PlaceComparator());
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("queue", getList());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Context_ControlQueue_Refurbish,map);
		notifyObservers(info);
	}
	
	/**
	 * 插入队列
	 * @param place
	 */
	private void insert(Place place) {
		// TODO Auto-generated method stub
		queue.add(place);
		Collections.sort(queue, new PlaceComparator());

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", place.getObject());
		map.put("queue", getList());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Context_ControlQueue_Insert,map);
		notifyObservers(info);
	}
	
	/**
	 * 从队列中取出
	 * @param place
	 */
	private void takeOut(Place place){
		if(!this.queue1.remove(place))
			this.queue2.remove(place);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", place.getObject());
		map.put("queue", getList());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Context_ControlQueue_Remove,map);
		notifyObservers(info);
	}
	
	public void moveToPrior(Place place){
		
		if(!this.queue1.remove(place))
			this.queue2.remove(place);
		
		this.priorQueue.add(place);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", place.getObject());
		map.put("queue", getList());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Context_ControlQueue_Move,map);
		notifyObservers(info);
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
		
		place.setCount(count + speed);          //当加入下一个队列时，表示下一个回合，所以要增加一次活力
		
		List<Place> list = otherQueue(queue);
		list.add(place);
		Collections.sort(list, new PlaceComparator());   //插入增加活力的place，需要重新排序
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", place.getObject());
		map.put("queue", getList());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Context_ControlQueue_Insert,map);
		notifyObservers(info);
	}
	
	private Integer indexOf(Place place){
		Integer index = priorQueue.indexOf(place);
		
		if(-1==index){
			index = queue.indexOf(place);
			if(-1!=index)
				index += priorQueue.size();
		}
		
		if(-1==index){
			List<Place> list = otherQueue(queue);
			index = list.indexOf(place);
			if(-1!=index){
				index += priorQueue.size();
				index += queue.size();
			}
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
	
	private List<Map<String, Object>> getList(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		for(int i=0;i<this.priorQueue.size();i++){
			Map<String, Object> map = new HashMap<String, Object>();
			Place place = this.priorQueue.get(i);
			map.put("position",indexOf(place));
			map.put("player", place.getObject());
			map.put("count", place.getCount());
			list.add(map);
		}
		
		for(int i=0;i<this.queue.size();i++){
			Map<String, Object> map = new HashMap<String, Object>();
			Place place = this.queue.get(i);
			map.put("position",indexOf(place));
			map.put("player", place.getObject());
			map.put("count", place.getCount());
			list.add(map);
		}
		
		for(int i=0;i<otherQueue(this.queue).size();i++){
			Map<String, Object> map = new HashMap<String, Object>();
			Place place  = otherQueue(this.queue).get(i);
			map.put("position",indexOf(place));
			map.put("player", place.getObject());
			map.put("count", place.getCount());
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
	
	public class Place {
		private IPlayer obj = null;
		private Integer count = 0;
		private Integer speed = 0;
		
		public Place(IPlayer object) {
			// TODO Auto-generated constructor stub
			this.obj = object;
			loadSpeed();
		}
		
		public void loadSpeed(){
			this.speed = 100;
		}
		
		public Integer getCount(){
			return count;
		}
		
		public void setCount(Integer count){
			this.count = count;
		}
		
		public Integer getSpeed() {
			return speed;
		}
		
		public IPlayer getObject(){
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

	@Override
	public Integer getLength() {
		// TODO Auto-generated method stub
		return this.queueList.size();
	}
}
