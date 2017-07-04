package org.cx.game.card.skill;

import org.cx.game.action.IUpgrade;
import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.card.magic.IMagic;
import org.cx.game.exception.RuleValidatorException;


public interface ISkill extends IMagic {
	
	public final static Integer Skill = 1008;
	
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
	public LifeCard getOwner();
	
	public void setOwner(LifeCard life);
	
	/**
	 * 技能使用范围
	 * @return
	 */
	public Integer getRange();
	
	public IUpgrade getUpgrade(); 
	
	/**
	 * 升级技能
	 * @throws RuleValidatorException
	 */
	public void upgrade() throws RuleValidatorException;

}
