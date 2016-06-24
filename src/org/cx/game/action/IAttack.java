package org.cx.game.action;

import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.observer.Observable;

/**
 * 系统将攻击这个动作抽象出来的目的是不同的生物攻击方式不同，规则也不同，比如近身攻击、远程攻击等
 * @author chenxian
 *
 */
public interface IAttack extends IAction {

	/**
	 * 近战攻击
	 */
	public final static Integer Mode_Near = 0;
	
	/**
	 * 远程攻击
	 */
	public final static Integer Mode_Far = 1;
	
	public Integer getMode();
	
	public void setMode(Integer mode);
	
	public void changeMode(Integer mode);
	
	public Integer getRange();
	
	public void setRange(Integer range);
	
	public void addToRange(Integer range);
	
	public Integer getSpeedChance();
	
	public void setSpeedChance(Integer speedChance);
	
	public void addToSpeedChance(Integer speedChance);
	
	public Integer getAtk();

	public void setAtk(Integer atk);
	
	public Integer getLockChance();
	
	public void setLockChance(Integer lockChance);
	
	public void addToAtk(Integer atk);
	
	public IAttack clone();
}
