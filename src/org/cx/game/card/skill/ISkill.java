package org.cx.game.card.skill;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.card.magic.IMagic;


public interface ISkill extends IMagic {
	
	/**
	 * 类名，不包含包名
	 * @return
	 */
	public String getCType();
	
	public String getName();
	
	/**
	 * 所属对象
	 * @return
	 */
	public ICard getOwner();
	
	public void setOwner(LifeCard life);
	
	/**
	 * 技能使用范围
	 * @return
	 */
	public Integer getRange();

}
