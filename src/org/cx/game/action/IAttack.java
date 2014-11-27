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
	
	public Integer getRange();
	
	public void setRange(Integer range);
	
	public static final Integer Type_Usually = 0;      //普通攻击
	
	public static final Integer Type_Puncture = 1;   //穿刺
	
	public static final Integer Type_Magic = 2;       //法术	
	
	public static final Integer Type_Large = 3;         //投石
	
	public static final Integer Type_Chaos = 4;         //混沌
	
	/**
	 * 攻击类型，普通/穿刺/法术/投石/混沌
	 * @return
	 */
	public Integer getType();
	
	public void setType(Integer type);
	
	public Integer getAccurateChance();
	
	public void setAccurateChance(Integer chance);
	
	public Integer getThumpChance();

	public void setThumpChance(Integer thumpChance);
	
	public Integer getSpeedChance();
	
	public void setSpeedChance(Integer speedChance);
	
	public void addToSpeedChance(Integer speedChance);
	
	public Integer getAtk();

	public void setAtk(Integer atk);
	
	public void addToAtk(Integer atk);
}
