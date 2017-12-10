package org.cx.game.action;

import org.cx.game.widget.building.IOption;

/**
 * 执行选项
 * @author chenxian
 *
 */
public interface IExecute extends IAction {
	
	public IOption getOwner();
}
