package org.cx.game.widget;

import org.cx.game.card.LifeCard;
import org.cx.game.card.trick.ITrick;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.observer.Observable;

/**
 * 陷阱列表，基于ground，与Cemetery类似
 * @author chenxian
 *
 */
public interface ITrickList extends IInterceptable, Observable {

	/**
	 * 加入容器
	 * @param trick
	 */
	public void add(ITrick trick);
	
	/**
	 * 从容器中移出
	 *
	 */
	public void remove(ITrick trick);
	
	/**
	 * 
	 * @return 容器当前元素的个数
 	 */
	public Integer getSize();
	
	/**
	 * 根据位置查找trick
	 * @param trick
	 * @return 
	 */
	public ITrick getTrick(Integer index);
	
	/**
	 * 
	 * @return 返回place
	 */
	public IPlace getOwner();
	
	/**
	 * 因为TrickList的getPosition(ICard)返回TrickList所在ground的position
	 * @param trick
	 * @return
	 */
	public Integer indexOf(ITrick trick);
	
	public Boolean contains(ITrick trick);
}
