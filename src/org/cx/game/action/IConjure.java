package org.cx.game.action;

import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.IActiveSkill;

/**
 * 施法
 * @author chenxian
 *
 */
public interface IConjure extends IAction {
	
	@Override
	public LifeCard getOwner();
}
