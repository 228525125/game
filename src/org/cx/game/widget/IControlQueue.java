package org.cx.game.widget;

import org.cx.game.intercepter.IInterceptable;
import org.cx.game.observer.Observable;
import org.cx.game.rule.IRule;
import org.cx.game.widget.ControlQueue.Place;

/**
 * 控制队列
 * @author chenxian
 *
 */
public interface IControlQueue extends Observable, IInterceptable{
	
	/**
	 * 将一个元素加入到控制列表中，插入时不用考虑位置
	 * @param object
	 */
	public void add(Object object);
	
	/**
	 * 
	 * @return 返回排序最靠前的一个元素
	 */
	public Object out();
	
	/**
	 * 子类需要提供适合自己的Observer（观察者），来适应不同的环境；该方法减少了多余的接口，又增加了扩展性；
	 * @return
	 */
	public IRule getRule();
	
	/**
	 * 刷新，当place中的life的speed发生变化时，立即刷新，该方法只用于rule
	 */
	public void refurbish();
	
	/**
	 * 用于life death后从queue中移除，该方法只用于rule
	 * @param object life 或者 player
	 */
	public void remove(Object object);
	
	/**
	 * 根据对象反查place
	 * @param object
	 * @return
	 */
	public Place getPlace(Object object);
	
	/**
	 * 将一个place移入优先队列
	 * @param place
	 */
	public void moveToPrior(Place place);
	
	/**
	 * 获取一次控制权需要消耗的能量
	 */
	public static final Integer consume = 100;
	
	public Integer getLength();
}
