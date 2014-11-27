package org.cx.game.card.skill;

import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.IRecover;
import org.cx.game.observer.Observable;
import org.cx.game.validator.IValidatable;

/**
 * 主动技能
 * @author 贤
 *
 */
public interface IActiveSkill extends ISkill,IInterceptable,Observable,IValidatable {

	/**
	 * 代号，用于command调用
	 * @return
	 */
	public String getCode();
	
	/**
	 * 消耗活力值
	 * @return
	 */
	public Integer getConsume();
	
	public void setConsume(Integer consume);
	
	public Integer getRange();
	
	/**
	 * 瞬发
	 */
	public static final Integer Velocity_Quick = 0;  
	
	/**
	 * 蓄力
	 */
	public static final Integer Velocity_Slow= 1;    
	
	/**
	 * 是否需蓄力
	 * @return
	 */
	public Integer getVelocity();
	
	/**
	 * 使用技能
	 * @param objects
	 */
	public void useSkill(Object...objects) throws RuleValidatorException;
}
