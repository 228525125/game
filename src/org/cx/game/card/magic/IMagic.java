package org.cx.game.card.magic;

import org.cx.game.exception.RuleValidatorException;
import org.cx.game.tag.ITag;

/**
 * 所有魔法的公共接口（trick、skill、buff）
 * @author 贤
 *
 */
public interface IMagic extends ITag {

	public Integer getId();
	
	/**
	 * 所属对象
	 * @return
	 */
	public Object getOwner();
	
	/**
	 * 是否启动法术效果，因为有些法术是针对特定对象的，如果对象出错效果将取消，
	 * 但法术动作不会取消
	 * @param args 效果作用单位
	 * @return
	 */
	public Boolean isTrigger(Object[] args);
	
	/**
	 * 影响，发挥作用，尽量把法术的逻辑写在这个方法里面，因为Affected会直接调用这个方法,
	 * 如果是buff，根据需要来写，例如中毒，每回合都会发生影响的，就要把效果写在这个方法
	 * 里面
	 * @param objects
	 */
	public void affect(Object...objects);		
	
	/**
	 * 物理buff
	 */
	public static final Integer Style_physical = 111;
	
	/**
	 * 魔法buff
	 */
	public static final Integer Style_Magic = 112;
	
	/**
	 * 移动限制
	 */
	public static final Integer Func_Astrict = 201;
	
	/**
	 * 直接伤害
	 */
	public static final Integer Func_Damage = 202;
	
	/**
	 * 召唤
	 */
	public static final Integer Func_Call = 203;
	
	/**
	 * 增益
	 */
	public static final Integer Func_Cure = 204;
	
	/**
	 * 损益
	 */
	public static final Integer Func_Loss = 205;
	
	/**
	 * 持续伤害
	 */
	public static final Integer Func_SustainedHarm = 206;
	
	/**
	 * 陷阱
	 */
	public static final Integer Func_Trick = 207;
	
	/**
	 * 冲锋
	 */
	public static final Integer Func_Bump = 208;
	
	/**
	 * 秘术
	 */
	public static final Integer Func_Mystery = 209;
	
	/**
	 * 其他功能
	 */
	public static final Integer Func_Other = 299;
	
	/**
	 * 描述
	 * @return
	 */
	public String getDepiction();
}
