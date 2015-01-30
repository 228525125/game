package org.cx.game.policy;

import org.cx.game.core.IPlayer;

public interface IPlayerPolicy extends IPolicy {
	
	@Override
	public IPlayer getOwner();
	
	/**
	 * 遍历手牌时，选出要出的牌
	 * @return 返回要出的卡片
	 */
	public IUseCardPolicy getUseCardPolicy();
	
	public Boolean hasNext();
}
