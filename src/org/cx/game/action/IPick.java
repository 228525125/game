package org.cx.game.action;

import org.cx.game.card.LifeCard;

/**
 * 捡起物品
 * @author chenxian
 *
 */
public interface IPick extends IAction {

	public final static Integer Pick_Range_Defautl = 1;
	
	@Override
	public LifeCard getOwner();
}
