package org.cx.game.widget;

import org.cx.game.intercepter.IInterceptable;
import org.cx.game.observer.Observable;

/**
 * 控制队列
 * @author chenxian
 *
 */
public interface IControlQueue extends Observable, IInterceptable{
	
	/**
	 * 将一个元素插入进队列，插入时不用考虑位置
	 * @param object life 或者 player
	 */
	public void insert(Object object);
	
	/**
	 * 
	 * @return 返回排序最靠前的一个元素
	 */
	public Object out();
	
	/**
	 * 用于life death后从queue中移除
	 * @param object life 或者 player
	 * @return 
	 */
	public void remove(Object object);
	
	/**
	 * 刷新，当place中的life的speed发生变化时，立即刷新
	 */
	public void refurbish();
	
	/**
	 * 获取一次控制权需要消耗的能量
	 */
	public static final Integer consume = 100;
}
