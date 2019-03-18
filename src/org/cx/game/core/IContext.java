package org.cx.game.core;

public interface IContext {

	public static final Integer Status_Prepare = 0;    //准备状态
	public static final Integer Status_Ready = 1;    //准备就绪，等待开始
	public static final Integer Status_Start = 2;      //开始状态
	
	/**
	 * 环境状态，当Context刚被创建时，处于Prepare状态，这时游戏规则不起作用，玩家做一些开战前的准备工作；
	 * 当游戏开始后，处于Start状态，这时游戏规则起作用；
	 * 这里的状态与比赛状态不是一个概念
	 * @return
	 */
	public Integer getStatus();
	
	/**
	 * 当前操作比赛的玩家对象
	 */
	public IPlayer getControlPlayer();
}
