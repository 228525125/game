package org.cx.game.widget;

import org.cx.game.card.LifeCard;

public interface IWeapon {

	public Integer getAtk();
	
	public void setAtk(Integer atk);
	
	public Integer getWear();
	
	public void setWear(Integer wear);
	
	/**
	 * 装备
	 */
	public void hand();
	
	/**
	 * 输出攻击
	 * @return
	 */
	public Integer output();
	
	/**
	 * 是否损坏
	 * @return
	 */
	public Boolean isBreakdown();
	
	/**
	 * 损坏
	 */
	public void breakdown();
	
	public LifeCard getOwner();
}
