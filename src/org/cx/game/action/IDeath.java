package org.cx.game.action;

import org.cx.game.action.Death.DeathAddToHpAction;
import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.intercepter.IRecover;
import org.cx.game.observer.Observable;

/**
 * 死亡
 * @author chenxian
 *
 */
public interface IDeath extends IAction {
	
	/**
	 * 当前生命值
	 * @return
	 */
	public Integer getHp();

	/**
	 * 隐式的改变HP
	 * @param hp
	 */
	public void setHp(Integer hp);
	
	public IAction getAddToHpAction();
	
	/**
	 * 改变HP值，可能是受到伤害，也可能是接收治疗
	 * @param hp
	 * @return 增加和减少的实际数据，例如hp=10，damage=-100，return=10；
	 * @throws RuleValidatorException 
	 */
	public void addToHp(Integer hp) throws RuleValidatorException;
	
	/**
	 * 生命值上限 = 初始生命值 + 等级生命值 + 额外生命值
	 * @return
	 */
	public Integer getHpLimit();
	
	public void setHpLimit(Integer hpLimit);
	
	public final static Integer Status_Live = 0;         //战斗
	public final static Integer Status_Death = 1;        //死亡
	public final static Integer Status_Exist = 2;        //存在
	
	/**
	 * 状态
	 * @return
	 */
	public Integer getStatus();
	
	public void setStatus(Integer status);
	
	public LifeCard getOwner();
}
