package org.cx.game.card.magic;

import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;

/**
 * 所有魔法的公共接口（trick、skill、buff）
 * @author 贤
 *
 */
public interface IMagic {

	/**
	 * 所属对象
	 * @return
	 */
	public Object getOwner();
	
	/**
	 * 影响，发挥作用，尽量把法术的逻辑写在这个方法里面，因为Affected会直接调用这个方法,
	 * 如果是buff，根据需要来写，例如中毒，每回合都会发生影响的，就要把效果写在这个方法
	 * 里面
	 * @param objects
	 */
	public void affect(Object...objects) throws RuleValidatorException;		
	
	/**
	 * 物理buff
	 */
	public static final Integer Style_physical = 11;
	
	/**
	 * 魔法buff
	 */
	public static final Integer Style_Magic = 12;
	
	/**
	 * 风格，法术、物理
	 * @return
	 */
	public Integer getStyle();
	
	/**
	 * 移动限制
	 */
	public static final Integer Func_Astrict = 101;
	
	/**
	 * 直接伤害
	 */
	public static final Integer Func_Damage = 102;
	
	/**
	 * 召唤
	 */
	public static final Integer Func_Call = 103;
	
	/**
	 * 增益
	 */
	public static final Integer Func_Gain = 104;
	
	/**
	 * 损益
	 */
	public static final Integer Func_Loss = 105;
	
	/**
	 * 持续伤害
	 */
	public static final Integer Func_SustainedHarm = 106;
	
	/**
	 * 陷阱
	 */
	public static final Integer Func_Trick = 107;
	
	/**
	 * 冲锋
	 */
	public static final Integer Func_Bump = 108;
	
	/**
	 * 其他功能
	 */
	public static final Integer Func_Other = 199;
	
	/**
	 * 功能类型
	 * @return
	 */
	public Integer getFunc();
}
