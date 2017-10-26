package org.cx.game.action;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.observer.Observable;

/**
 * 召唤
 * @author chenxian
 *
 */
public interface ICall extends IAction {
	
	/**
	 * 消耗能量
	 * @return
	 */
	public Integer getConsume();
	
	public void setConsume(Integer consume);
	
	//public void addToConsume(Integer consume);
	
	public void updateConsume();
	
	/**
	 * 人口
	 * @return
	 */
	public Integer getRation();
	
	public void setRation(Integer ration);
	
	public LifeCard getOwner();
}
