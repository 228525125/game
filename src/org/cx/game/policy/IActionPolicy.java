package org.cx.game.policy;

import org.cx.game.action.IAction;

public interface IActionPolicy extends IPolicy, IPriority {
	
	@Override
	public IAction getOwner();
}
