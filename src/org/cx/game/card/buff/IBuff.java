package org.cx.game.card.buff;

import org.cx.game.card.LifeCard;
import org.cx.game.card.magic.IMagic;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.IRecover;
import org.cx.game.observer.Observable;

/**
 * 状态
 * @author chenxian
 *
 */
public interface IBuff extends IInterceptable,Observable,IIntercepter,IMagic,IRecover{
	
	public final static Integer Buff = 1009;
	
	/**
	 * 类名，不包含包名
	 * @return
	 */
	public String getCType();
	
	public String getName();
	
	public static final Integer Max_Bout = 999;
	
	/**
	 * 持续回合
	 * @return
	 */
	public Integer getBout();
	
	public void setBout(Integer bout);
	
	/**
	 * 生效，表示buff显示存在
	 */
	public void effect();
	
	/**
	 * 失效，与effect对应
	 */
	public void invalid();
	
	/**
	 * 受影响者
	 */
	public LifeCard getOwner();
	
	/**
	 * 通知类型
	 * @return
	 */
	public String getAction();
	
	/**
	 * 是否可以叠加
	 * @return
	 */
	public Boolean isDuplication();
	
	public void setDuplication(Boolean duplication);
	
	/**
	 * buff增加的攻击力，在extraAtk中体现
	 * @return
	 */
	public Integer getAtk();
	
	/**
	 * buff增加的防御力，在extraAtk中体现
	 * @return
	 */
	public Integer getDef();
	
	/**
	 * 受益，不包含系统级的buff
	 */
	public static final Integer Type_Benefit = 121;
	
	/**
	 * 受损，不包含系统级的buff
	 */
	public static final Integer Type_Harm = 122;
	
	/**
	 * 中性
	 */
	public static final Integer Type_Neutral = 123;
	
}
