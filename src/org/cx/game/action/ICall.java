package org.cx.game.action;

import java.util.Map;

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
	 * 消耗资源
	 * @return 资源类型，资源数量
	 */
	public Map<String,Integer> getConsume();
	
	public void setConsume(Map<String,Integer> consume);
	
	public void updateConsume();
	
	/**
	 * 人口
	 * @return
	 */
	public Integer getRation();
	
	public void setRation(Integer ration);
	
	/**
	 * 人数
	 * @return
	 */
	public Integer getNop();
	
	public void setNop(Integer nop);
	
	public LifeCard getOwner();
}
