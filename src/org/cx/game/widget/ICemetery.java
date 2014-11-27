package org.cx.game.widget;

import java.util.ArrayList;
import java.util.Iterator;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.core.Context;
import org.cx.game.core.IPlayer;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.observer.Observable;

/**
 * 墓地，是基于ground每个格子都带一个墓地
 * @author chenxian
 *
 */
public interface ICemetery extends IInterceptable, Observable {
	
	/**
	 * 加入容器
	 * @param life
	 */
	public void add(LifeCard life);
	
	/**
	 * 从容器中移出
	 *
	 */
	public void remove(LifeCard life);
	
	/**
	 * 
	 * @return 容器当前元素的个数
 	 */
	public Integer getSize();
	
	/**
	 * 根据位置查找life
	 * @param life
	 * @return 
	 */
	public LifeCard getLife(Integer index);
	
	/**
	 * 
	 * @return 返回place
	 */
	public IPlace getPlace();
	
	/**
	 * 因为Cemetery的getPosition(ICard)返回Cemetery所在ground的position
	 * @param life
	 * @return
	 */
	public Integer indexOf(LifeCard life);
	
	public Boolean contains(LifeCard life);

}