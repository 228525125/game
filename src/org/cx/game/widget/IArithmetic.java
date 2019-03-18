package org.cx.game.widget;

import java.util.List;

public interface IArithmetic {

	/**
	 * 相对于目标位置的任意方向，stand与target位置必须是相邻的
	 * @param stand 站位
	 * @param target 目标位置
	 * @return 方向
	 */
	public Integer getDirection(Integer stand, Integer target);
	
	/**
	 * 根据方向查询相邻位置
	 * @param stand 站位
	 * @param direction 方向
	 * @return 位置坐标
	 */
	public Integer getPosition(Integer stand, Integer direction);
	
	/**
	 * 两侧的位置，stand与target位置必须是相邻的
	 * @param stand 站位
	 * @param direction 方向
	 * @return
	 */
	public List<Integer> twoFlanks(Integer stand, Integer direction);
	
	/**
	 * 两点之间的矩阵，包含起点和终点
	 * @param start 左上角
	 * @param stop  右下角
	 * @return
	 */
	public List<Integer> rectangle(Integer start, Integer stop);
	
	/**
	 * 获得两点之间的最短路线，并且start<>stop
	 * @param start 起点
	 * @param stop 终点
	 * @param MAP 地形
	 * @param hit 障碍物
	 * @return LinkedList<Node> 包含启动和终点，如果stop不可到达则返回null
	 */
	public List route(Integer start, Integer stop, int[][] MAP, int[] hit);
}
