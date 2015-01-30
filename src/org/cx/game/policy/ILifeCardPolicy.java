package org.cx.game.policy;

import org.cx.game.card.LifeCard;

public interface ILifeCardPolicy extends IPolicy {

	@Override
	public LifeCard getOwner();
	
	/**
	 * 遍历手牌时，选出要出的牌
	 * @return 返回要出的卡片
	 */
	public IActionPolicy getActionPolicy();
	
	public Boolean hasNext();
}
