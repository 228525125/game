package org.cx.game.policy;

import org.cx.game.card.ICard;

/**
 * 卡片策略
 * @author chenxian
 *
 */
public interface IUseCardPolicy extends IPolicy, IPriority {
	
	@Override
	public ICard getOwner();
}
