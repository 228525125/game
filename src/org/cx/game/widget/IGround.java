package org.cx.game.widget;

import java.util.List;
import java.util.Map;

import org.cx.game.core.IPlayer;
import org.cx.game.corps.AbstractCorps;
import org.cx.game.widget.building.IBuilding;
import org.cx.game.widget.treasure.ITreasure;

/**
 * position用二维坐标系表示，中间用0000隔开，例如(1,2) = 100002
 * @author jiuhuan
 *
 */
public interface IGround {
	
	/**
	 * 边界上
	 */
	public static final Integer Equal = 0;
	
	/**
	 * 范围内
	 */
	public static final Integer Contain = 1;
	
	public static final Integer Relative_Top = 0;
	public static final Integer Relative_LeftTop = 10;
	public static final Integer Relative_Left = 9;
	public static final Integer Relative_LeftBottom = 8;
	public static final Integer Relative_Bottom = 6;
	public static final Integer Relative_RightTop = 2;
	public static final Integer Relative_Right = 3;
	public static final Integer Relative_RightBottom = 4;
	
	public Integer getId();
	
	/**
	 * 战场地图名称
	 * @return
	 */
	public String getName();
	
	/**
	 * 战场x边界
	 * @return
	 */
	public Integer getXBorder();
	
	/**
	 * 战场y边界
	 * @return
	 */
	public Integer getYBorder();
	
	/**
	 * 该方法在对象被创建后调用
	 */
	public void afterConstruct();
	
	/**
	 * 在指定位置放置一个corps
	 * @param position 位置
	 * @param corps
	 */
	public void placementCorps(Integer position, AbstractCorps corps);
	
	public AbstractCorps getCorps(Integer position);
	
	public Boolean contains(AbstractCorps corps);
	
	/**
	 * 返回战场上所有Corps，包括墓地；    容易产生误解，建议用getLivingCorpsList和getDeadCorpsList代替
	 * @return
	 
	public List<AbstractCorps> getCorpsList();*/
	
	/**
	 * 战场上活着的corps
	 * @return
	 */
	public List<AbstractCorps> getLivingCorpsList();
	
	/**
	 * 战场上死亡的corps
	 * @return
	 */
	public List<AbstractCorps> getDeadCorpsList();
	
	/**
	 * 进入墓地，例如在战场上死亡
	 * @param corps 
	 */
	public void inCemetery(AbstractCorps corps);
	
	/**
	 * 移出墓地，例如英雄复活
	 * @param corps
	 */
	public void outCemetery(AbstractCorps corps);
	
	/**
	 * 根据卡片查找位置
	 * @param corps
	 * @return
	 */
	public Integer getPosition(AbstractCorps corps);
	
	/**
	 * 放置一个建筑物到战场
	 * @param position
	 * @param building
	 */
	public void placementBuilding(Integer position, IBuilding building);

	public List<IBuilding> getBuildingList();
	
	/**
	 * 根据坐标，仅用于CommandBuffer
	 * @param position 坐标
	 * @return
	 */
	public AbstractPlace getPlace(Integer position);
	
	/**
	 * 在地图上增加一块区域（地图是由若干区域组成）
	 * @param place
	 */
	public void addPlace(AbstractPlace place);
	
	/**
	 * 
	 * @return 地形数据
	 */
	public Map<Integer, Integer> getLandformMap();
	
	/**
	 * 地图上的物品
	 * @return
	 */
	public List<ITreasure> getTreasureList();
	
	/**
	 * 移除物品
	 * @param treasure
	 */
	public void removeTreasure(ITreasure treasure);
	
	/**
	 * 两个坐标之间的最短距离，不考虑地形
	 * @param start
	 * @param stop
	 * @return
	 */
	public Integer distance(Integer start, Integer stop);
	
	/**
	 * 两个坐标之间的最短距离，考虑地形
	 * @param start 必须为起点
	 * @param stop 必须为需要测试的点
	 * @param moveType
	 * @return 如果stop不可到达，即MAP中为-1，则返回9999
	 */
	public Integer distance(Integer start, Integer stop, Integer moveType, IPlayer control);
	
	/**
	 * 获取指定距离的区域，考虑障碍物
	 * @param position 指定坐标
	 * @param step 距离，注意step必须大于0，否则无意义
	 * @param type  0:刚好在边界上;1范围内;2范围外;
	 * @param moveType  移动类型
	 * @return
	 */
	public List<Integer> areaForDistance(Integer position, Integer step, Integer type, Integer moveType, IPlayer control);
	
	/**
	 * 获取指定距离的区域，不考虑障碍物
	 * @param position
	 * @param step
	 * @param type
	 * @return
	 */
	public List<Integer> areaForDistance(Integer position, Integer step, Integer type);
	
	/**
	 * 相对与目标位置，是上下左右其中一种，stand与target位置必须是相邻的
	 * @param stand 站位
	 * @param target 目标位置
	 * @return
	 */
	public Integer getDirection(Integer stand, Integer target);
	
	/**
	 * 根据方向查询相邻位置
	 * @param stand 站位
	 * @param Direction 方向
	 * @return
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
	 * 从起点到终点的最短路径，根据移动范围来获取路径上的那个点
	 * @param stand 当前位置
	 * @param dest 目标位置
	 * @param step 步数
	 * @param moveType 移动类型
	 * @return
	 */
	public Integer getPointByWay(Integer stand, Integer dest, Integer step, Integer moveType, IPlayer control);
	
	/**
	 * 从容器中移出，即corps不存在于place及cemetery；        该方法容易产生误解，不建议保留
	 
	public Boolean removeCorps(AbstractCorps corps);*/

}
