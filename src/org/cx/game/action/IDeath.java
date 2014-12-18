package org.cx.game.action;

import org.cx.game.card.LifeCard;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.intercepter.IRecover;
import org.cx.game.observer.Observable;

/**
 * 死亡
 * @author chenxian
 *
 */
public interface IDeath extends IAction, IRecover{
	
	public Integer getHp();

	/**
	 * 隐式的改变HP
	 * @param hp
	 */
	public void setHp(Integer hp);
	
	
	public final static Integer Status_Live = 0;         //战斗
	public final static Integer Status_Death = 1;        //死亡
	public final static Integer Status_Exist = 2;        //存在
	
	/**
	 * 状态
	 * @return
	 */
	public Integer getStatus();
	
	public void setStatus(Integer status);
	
	
	/**
	 * 普通攻击造成伤害时，调用该方法
	 * @param hp
	 */
	public void attackToDamage(Integer hp);
	
	/**
	 * 魔法攻击造成伤害时，调用该方法
	 * @param hp
	 */
	public void magicToHp(Integer hp);
}
