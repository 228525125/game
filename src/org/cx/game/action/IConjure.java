package org.cx.game.action;

import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.IActiveSkill;

/**
 * 施法
 * @author chenxian
 *
 */
public interface IConjure extends IAction {
	
	public final static Integer Max_Power = 100;
	
	/**
	 * 法力值
	 * @return
	 */
	public Integer getPower();
	
	public void setPower(Integer power);
	
	@Override
	public LifeCard getOwner();
}
