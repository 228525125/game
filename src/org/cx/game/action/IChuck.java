package org.cx.game.action;

import org.cx.game.card.ICard;

public interface IChuck extends IAction {

	@Override
	public ICard getOwner();
}
