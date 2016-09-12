package org.cx.game.action;

import org.cx.game.card.LifeCard;

public interface IAffected extends IAction {
		
	@Override
	public LifeCard getOwner();
}
