package org.cx.game.npc;

public interface IPlayerPolicy {

	/**
	 * 比赛前置策略
	 *
	 */
	public void prefixPolicy();
	
	/**
	 * 调用部署策略
	 *
	 */
	public void deployPolicy();
	
	/**
	 * 调用攻击策略
	 *
	 */
	public void attackPolicy();
	
	/**
	 * 调用防守策略
	 *
	 */
	public void defendPolicy();
	
	/**
	 * 比赛回合结束时调用
	 *
	 */
	public void finishPolicy();
}
