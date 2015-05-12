package org.cx.game.card.skill;

import org.cx.game.card.LifeCard;


public interface ISkill extends IMagic {
	
	public String getSType();
	
	public String getName();
	
	/**
	 * 所属对象
	 * @return
	 */
	public LifeCard getOwner();
	
	public void setOwner(LifeCard life);
	
	/**
	 * 技能使用范围
	 * @return
	 */
	public Integer getRange();

}
