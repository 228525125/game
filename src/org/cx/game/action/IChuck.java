package org.cx.game.action;

import org.cx.game.card.LifeCard;

public interface IChuck extends IAction {

	@Override
	public LifeCard getOwner();
}
