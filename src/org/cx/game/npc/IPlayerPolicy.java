package org.cx.game.npc;

import org.cx.game.card.ICard;

public interface IPlayerPolicy {
	
	/**
	 * 调用部署策略
	 *
	 */
	public void deployPolicy();
	
	/**
	 * 当手牌满了，需要丢弃时该方法被调用
	 * @param card 与当前卡片比较的卡片
	 * @return 返回要丢弃的卡片
	 */
	public ICard chuckPolicy(ICard dest, ICard source);
	
	/**
	 * 遍历手牌时，选出要出的牌
	 * @param card 与当前卡片比较的卡片
	 * @return 返回要出的卡片
	 */
	public ICard pushPolicy(ICard dest, ICard source);
}
