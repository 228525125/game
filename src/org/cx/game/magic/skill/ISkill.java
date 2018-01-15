package org.cx.game.magic.skill;

import org.cx.game.action.IUpgrade;
import org.cx.game.corps.Corps;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.magic.IMagic;


public interface ISkill extends IMagic {
	
	public final static Integer Skill = 1008;
	
	public String getName();
	
	/**
	 * 所属对象
	 * @return
	 */
	public Corps getOwner();
	
	public void setOwner(Corps corps);
	
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
