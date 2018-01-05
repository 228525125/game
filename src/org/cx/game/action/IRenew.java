package org.cx.game.action;

import org.cx.game.card.LifeCard;

/**
 * 据点生物的刷新
 * @author chenxian
 *
 */
public interface IRenew extends IAction{

	@Override
	public LifeCard getOwner();
}
