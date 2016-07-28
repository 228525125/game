package org.cx.game.action;

public interface IActivate extends IAction {

	public Boolean getActivation();
	
	/**
	 * 隐式改变状态
	 * @param activate
	 */
	public void setActivation(Boolean activate);
}
