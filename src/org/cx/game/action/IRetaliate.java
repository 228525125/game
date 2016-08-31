package org.cx.game.action;

import org.cx.game.card.LifeCard;

/**
 * 反击
 * @author chenxian
 *
 */
public interface IRetaliate extends IAction {

	public Boolean getFightBack();
	
	public void setFightBack(Boolean fightBack);
	
	public LifeCard getOwner();
}
