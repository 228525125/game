package org.cx.game.policy;

public interface IPlayerPolicy {
	
	/**
	 * 遍历手牌时，选出要出的牌
	 * @return 返回要出的卡片
	 */
	public ICardPolicy getCardPolicy();
	
	public Boolean hasNext();
}
