package org.cx.game.widget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cx.game.action.AbstractAction;
import org.cx.game.action.ActionProxyHelper;
import org.cx.game.action.IAction;
import org.cx.game.core.AbstractPlayer;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.tools.CommonIdentifier;

/**
 * 控制队列
 * @author chenxian
 *
 */
public class ControlQueue {

	/**
	 * 获取一次控制权需要消耗的能量
	 */
	static final Integer consume = 100;
	
	private Map<String,List<IIntercepter>> intercepterList = new HashMap<String,List<IIntercepter>>();
	private List<Place> queueList = new ArrayList<Place>();                // queue1 + queue2
	private List<Place> queue1 = new ArrayList<Place>();                   // 两个队列用于切换，实现按优先级排序
	private List<Place> queue2 = new ArrayList<Place>();
	private Map<Integer,List<Place>> map = new HashMap<Integer,List<Place>>(); 
	private List<Place> queue = null;        //当前队列
	private Integer curQueue = 0;	
	
	private InsertAction insertAction = null;
	private RefurbishAction refurbishAction = null;
	private MoveToPriorAction moveToPriorAction = null;
	private TakeOutAction takeOutAction = null;
	private InsertOtherQueueAction insertOtherQueueAction = null;
	
	private List<Place> priorQueue = new ArrayList<Place>();     //这个队列的对象会优先出场，例如具有突袭效果的随从；
	
	public ControlQueue() {
		// TODO Auto-generated constructor stub
		map.put(1, queue1);
		map.put(2, queue2);
		this.queue = this.queue1;
		this.curQueue = 1;
	}
	
	public Integer getLength() {
		// TODO Auto-generated method stub
		return this.queueList.size();
	}
	
	/**
	 * 将一个元素加入到控制列表中，插入时不用考虑位置
	 * @param object
	 */
	public void add(AbstractPlayer player) {
		// TODO Auto-generated method stub
		Place place = new Place(player);
		
		/*
		 * 这里为什么直接插入第二队列，它的效果就像你排队办事一样，你总是从最后一个排起
		 */
		place.setCount(0);
		insertOtherQueue(place);
		
		this.queueList.add(place);
	}
	
	/**
	 * 用于corps death后从queue中移除，该方法只用于rule
	 * @param object corps 或者 player
	 */
	public void remove(AbstractPlayer player) {
		// TODO Auto-generated method stub
		Place place = new Place(player);
		takeOut(place);
		
		this.queueList.remove(place);
	}
	
	/**
	 * 根据对象反查place
	 * @param object
	 * @return
	 */
	public Place getPlace(AbstractPlayer player) {
		// TODO Auto-generated method stub
		for(Place place : this.queueList){
			if(place.getObject().equals(player))
				return place;
		}
		return null;
	}
	
	/**
	 * 
	 * @return 返回排序最靠前的一个元素
	 */
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
			insertOtherQueue(place);          //如果小于额定消耗，则进入下一个queue
		}
		
		return object;
	}
	
	/**
	 * 将一个place移入优先队列
	 * @param place
	 */
	public void moveToPrior(Place place){
		IAction action = new ActionProxyHelper(getMoveToPriorAction());
		action.action(place);
	}
	
	/**
	 * 刷新，当place中的corps的speed发生变化时，立即刷新，该方法只用于rule
	 */
	public void refurbish() {
		// TODO Auto-generated method stub
		IAction action = new ActionProxyHelper(getRefurbishAction());
		action.action();
	}
	
	class MoveToPriorAction extends AbstractAction implements IAction {

		@Override
		public void action(Object... objects) {
			// TODO Auto-generated method stub
			Place place = (Place) objects[0];
			
			if(!queue1.remove(place))
				queue2.remove(place);
			
			priorQueue.add(place);
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", place.getObject());
			map.put("queue", getList());
			NotifyInfo info = new NotifyInfo(CommonIdentifier.Context_ControlQueue_Move,map);
			notifyObservers(info);
		}
		
	}
	
	class RefurbishAction extends AbstractAction implements IAction {

		@Override
		public void action(Object... objects) {
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
			NotifyInfo info = new NotifyInfo(CommonIdentifier.Context_ControlQueue_Refurbish,map);
			notifyObservers(info);
		}
		
	}
	
	class InsertAction extends AbstractAction implements IAction {

		@Override
		public void action(Object... objects) {
			// TODO Auto-generated method stub
			Place place = (Place) objects[0];
			
			queue.add(place);
			Collections.sort(queue, new PlaceComparator());
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", place.getObject());
			map.put("queue", getList());
			NotifyInfo info = new NotifyInfo(CommonIdentifier.Context_ControlQueue_Insert,map);
			notifyObservers(info);
		}
		
	}
	
	class InsertOtherQueueAction extends AbstractAction implements IAction {

		@Override
		public void action(Object... objects) {
			// TODO Auto-generated method stub
			Place place = (Place) objects[0];
			
			Integer count = place.getCount();
			Integer consume = 100;
			Integer speed = 0;
			if (place.getObject() instanceof AbstractPlayer) {
				speed = consume;
			}
			
			place.setCount(count + speed);          //当加入下一个队列时，表示下一个回合，所以要增加一次活力
			
			List<Place> list = otherQueue(queue);
			list.add(place);
			Collections.sort(list, new PlaceComparator());   //插入增加活力的place，需要重新排序
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", place.getObject());
			map.put("queue", getList());
			NotifyInfo info = new NotifyInfo(CommonIdentifier.Context_ControlQueue_Insert,map);
			notifyObservers(info);
		}
		
	}
	
	class TakeOutAction extends AbstractAction implements IAction {

		@Override
		public void action(Object... objects) {
			// TODO Auto-generated method stub
			Place place = (Place) objects[0];
			
			if(!queue1.remove(place))
				queue2.remove(place);
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", place.getObject());
			map.put("queue", getList());
			NotifyInfo info = new NotifyInfo(CommonIdentifier.Context_ControlQueue_Remove,map);
			notifyObservers(info);
		}
		
	}
	
	private IAction getMoveToPriorAction() {
		if(null==this.refurbishAction){
			this.refurbishAction = new RefurbishAction();
			this.refurbishAction.setOwner(this);
		}
		return this.refurbishAction;
	}
	
	private IAction getRefurbishAction() {
		if(null==this.refurbishAction){
			this.refurbishAction = new RefurbishAction();
			this.refurbishAction.setOwner(this);
		}
		return this.refurbishAction;
	}
	
	
	private IAction getInsertAction() {
		if(null==this.insertAction){
			this.insertAction = new InsertAction();
			this.insertAction.setOwner(this);
		}
		return this.insertAction;
	}
	
	private IAction getInsertOtherQueue() {
		if(null==this.insertOtherQueueAction){
			this.insertOtherQueueAction = new InsertOtherQueueAction();
			this.insertOtherQueueAction.setOwner(this);
		}
		return this.insertOtherQueueAction;
	}
	
	private IAction getTakeOutAction() {
		if(null==this.takeOutAction){
			this.takeOutAction = new TakeOutAction();
			this.takeOutAction.setOwner(this);
		}
		return this.takeOutAction;
	}
	
	/**
	 * 插入队列
	 * @param place
	 */
	private void insert(Place place) {
		// TODO Auto-generated method stub
		IAction action = new ActionProxyHelper(getInsertAction());
		action.action(place);
	}
	
	/**
	 * 插入下一个队列
	 * @param place
	 */
	private void insertOtherQueue(Place place){
		IAction action = new ActionProxyHelper(getInsertOtherQueue());
		action.action(place);
	}
	
	/**
	 * 从队列中取出
	 * @param place
	 */
	private void takeOut(Place place){
		IAction action = new ActionProxyHelper(getTakeOutAction());
		action.action(place);
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
	
	public class Place {
		private AbstractPlayer obj = null;
		private Integer count = 0;
		private Integer speed = 0;
		
		public Place(AbstractPlayer object) {
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
		
		public AbstractPlayer getObject(){
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
