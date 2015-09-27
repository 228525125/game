package org.cx.game.action;

import org.cx.game.card.LifeCard;

public interface IAttacked extends IAction {

	public static final Integer Type_Non = 0;    //无护甲
	
	public static final Integer Type_Light = 1;    //轻型护甲
	
	public static final Integer Type_Middle = 2;  //中型护甲
	
	public static final Integer Type_Large = 3;  //大型护甲
	
	public static final Integer Type_Wall = 4;   //城墙
	
	/**
	 * 护甲类型
	 * @return
	 */
	public Integer getArmourType();
	
	public void setArmourType(Integer armourType);
	
	/**
	 * 反击几率 基准100.00
	 * @return
	 */
	public Integer getAttackBackChance();

	public void setAttackBackChance(Integer attackBackChance);

	/**
	 * 闪避几率 基准100.00
	 * @return
	 */
	public Integer getDodgeChance();

	public void setDodgeChance(Integer dodgeChance);
	
	public void addToDodgeChance(Integer dodgeChance);

	/**
	 * 格挡几率 基准100
	 * @return
	 */
	public Integer getParryChance();

	public void setParryChance(Integer parryChance);
	
	/**
	 * 免伤比，基准100
	 * @return
	 */
	public Integer getImmuneDamageRatio();

	public void setImmuneDamageRatio(Integer damageChance);
	
	public void addToImmuneDamageRatio(Integer damageChance);
}
