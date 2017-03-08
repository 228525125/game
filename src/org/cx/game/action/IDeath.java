package org.cx.game.action;

import org.cx.game.card.ICard;
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
	
	/**
	 * hp
	 * @return
	 */
	public Integer getHp();

	/**
	 * 隐式的改变HP
	 * @param hp
	 */
	public void setHp(Integer hp);
	
	/**
	 * 改变HP值，可能是受到伤害，也可能是接收治疗
	 * @param hp
	 */
	public void addToHp(Integer hp);
	
	/**
	 * HP上限
	 * @return
	 */
	public Integer getHplimit();
	
	/**
	 * 这里仅提供setHplimit方法，是因为hp上限不需要显示的操作，它是一个隐含的游戏规则；
	 * @param hplimit
	 */
	public void setHplimit(Integer hplimit);
	
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
