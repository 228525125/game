package org.cx.game.widget;

import org.cx.game.card.LifeCard;

public interface IWeapon {

	public String getName();
	
	/**
	 * 攻击力
	 * @return
	 */
	public Integer getAtk();
	
	public void setAtk(Integer atk);
	
	public void addToAtk(Integer atk);
	
	/**
	 * 耐久
	 * @return
	 */
	public Integer getWear();
	
	public void setWear(Integer wear);
	
	public void addToWear(Integer wear);
	
	/**
	 * 输出攻击，在AttackRule中输出攻击 (暂时停用)
	 * @return
	 */
	@Deprecated
	public Integer output();
	
	/**
	 * 是否损坏，用于AttackRule 和  AttackedRule，在AttackRule中输出攻击，在AttackedRule中判断损坏
	 * @return
	 
	public Boolean isBreakdown();*/
	
	/**
	 * 损坏
	 */
	public void breakdown();
	
	/**
	 * 装备
	 */
	public void equip();
	
	public LifeCard getOwner();
}
