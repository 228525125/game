package org.cx.game.core;

import org.cx.game.command.CommandBuffer;

public interface IPlayer {
	
	public IContext getContext();

	/**
	 * 命令内存
	 * @return
	 */
	public CommandBuffer getCommandBuffer();
	
	/**
	 * 游戏主机
	 * @return
	 */
	public IHost getHost();
	
	/**
	 * 阵营，它根据比赛中的位置来定的
	 * @return
	 */
	public Integer getTroop();
}
