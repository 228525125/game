package org.cx.game.action;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.observer.Observable;
import org.cx.game.widget.IWeapon;

/**
 * 系统将攻击这个动作抽象出来的目的是不同的生物攻击方式不同，规则也不同，比如近身攻击、远程攻击等
 * @author chenxian
 *
 */
public interface IAttack extends IAction {

	/**
	 * 近战攻击
	 */
	public final static Integer Mode_Near = 123;
	
	/**
	 * 远程攻击
	 */
	public final static Integer Mode_Far = 124;
	
	public Integer getMode();
	
	public void setMode(Integer mode);
	
	public void changeMode(Integer mode);
	
	public Integer getRange();

	/**
	 * 隐式使用，不涉及到模块以外的变更时使用；
	 * @param range
	 */
	public void setRange(Integer range);
	
	public void addToRange(Integer range);
	
	public Integer getAtk();

	/**
	 * 隐式使用，不涉及到模块以外的变更时使用；
	 * @param atk
	 */
	public void setAtk(Integer atk);
	
	public void addToAtk(Integer atk);
	
	public Integer getLockChance();
	
	/**
	 * 隐式使用，不涉及到模块以外的变更时使用；
	 * @param lockChance
	 */
	public void setLockChance(Integer lockChance);
	
	public void addToLockChance(Integer lockChance);
	
	/**
	 * 攻击方式，是否是反击
	 * @return
	 */
	public Boolean getCounterAttack();
	
	public void setCounterAttack(Boolean counterAttack);
	
	/**
	 * 是否能够攻击
	 * @return
	 */
	public Boolean getAttackable();
	
	public void setAttackable(Boolean attackable);
	
	/**
	 * 武器
	 * @return
	 */
	public IWeapon getWeapon();
	
	/**
	 * 拿起武器
	 * @param weapon
	 */
	public void handWeapon(IWeapon weapon);
	
	public IAttack clone();
	
	public LifeCard getOwner();
}
