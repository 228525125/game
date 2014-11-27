package org.cx.game.action;

import org.cx.game.card.LifeCard;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.observer.Observable;

/**
 * 升级，注意升级等同于召唤，但不升级事件不会调用召唤事件
 * @author chenxian
 *
 */
public interface ISwap extends IAction {
	
	/**
	 * 消耗能量，根据交换的生物不同而不同
	 * @return
	 */
	public Integer getConsume(LifeCard swaped);
}
