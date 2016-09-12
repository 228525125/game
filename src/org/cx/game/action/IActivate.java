package org.cx.game.action;

import org.cx.game.card.LifeCard;

public interface IActivate extends IAction {

	public Boolean getActivation();
	
	/**
	 * 隐式改变状态
	 * @param activate
	 */
	public void setActivation(Boolean activate);
	
	public LifeCard getOwner();
}
