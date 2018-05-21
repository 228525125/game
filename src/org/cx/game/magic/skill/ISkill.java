package org.cx.game.magic.skill;

import java.util.List;

import org.cx.game.action.IAction;
import org.cx.game.corps.AbstractCorps;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.magic.IMagic;


public interface ISkill extends IMagic {
	
	public final static Integer Skill = 1008;
	
	public String getName();
	
	/**
	 * 所属对象
	 * @return
	 */
	public AbstractCorps getOwner();
	
	public void setOwner(AbstractCorps corps);
	
	public List<Integer> getConjureRange();
	
	/**
	 * 技能使用范围
	 * @return
	 */
	public Integer getRange();
	
	public IAction getUpgrade();
	
	/**
	 * 升级技能
	 * @throws RuleValidatorException
	 */
	public void upgrade();

}
