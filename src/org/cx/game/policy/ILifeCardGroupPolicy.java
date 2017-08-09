package org.cx.game.policy;

import org.cx.game.card.LifeCard;

public interface ILifeCardGroupPolicy extends IGroupPolicy {

	@Override
	public LifeCard getOwner();
}
