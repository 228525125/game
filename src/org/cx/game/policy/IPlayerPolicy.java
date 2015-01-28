package org.cx.game.policy;

import org.cx.game.card.ICard;

public interface IPlayerPolicy {
	
	/**
	 * 调用部署策略
	 *
	 */
	public void deployPolicy();
	
	/**
	 * 当手牌满了，需要丢弃时该方法被调用
	 * @return 返回要丢弃的卡片
	 */
	public ICard chuckPolicy();
	
	/**
	 * 遍历手牌时，选出要出的牌
	 * @return 返回要出的卡片
	 */
	public ICard pushPolicy();
}
