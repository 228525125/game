package org.cx.game.widget;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.card.MagicCard;
import org.cx.game.card.skill.IActiveSkill;
import org.cx.game.card.skill.ISkill;
import org.cx.game.core.IPlayer;
import org.cx.game.policy.IPolicyGroup;
import org.cx.game.widget.building.IBuilding;
import org.cx.game.widget.building.IOption;
import org.cx.game.widget.treasure.ITreasure;

/**
 * position用二维坐标系表示，中间用0000隔开，例如(1,2) = 100002
 * @author jiuhuan
 *
 */
public interface IGround extends IContainer{
	
	public static final Integer Landform = 1011;
	
	public static String space = "8008";                               //位置坐标间隔符
	
	public LifeCard getCard(Integer position);
	
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
	public Integer distance(Integer start, Integer stop, Integer moveType);
	
	/**
	 * 边界上
	 */
	public static final Integer Equal = 0;
	
	/**
	 * 范围内
	 */
	public static final Integer Contain = 1;
	
	/**
	 * 获取指定距离的区域，考虑障碍物
	 * @param position 指定坐标
	 * @param step 距离，注意step必须大于0，否则无意义
	 * @param type  0:刚好在边界上;1范围内;2范围外;
	 * @param moveType  移动类型
	 * @return
	 */
	public List<Integer> areaForDistance(Integer position, Integer step, Integer type, Integer moveType);
	
	public final static Integer Shade_Range = 5;
	
	/**
	 * 获取指定距离的区域，不考虑障碍物
	 * @param position
	 * @param step
	 * @param type
	 * @return
	 */
	public List<Integer> areaForDistance(Integer position, Integer step, Integer type);
	
	
	/**
	 * 根据坐标
	 * @param position 坐标
	 * @return
	 */
	public IPlace getPlace(Integer position);
	
	/**
	 * 获取建筑的坐标
	 * @param player 玩家
	 * @return
	 */
	public List<Integer> getBuildingPosition(IPlayer player);
	
	/**
	 * 获取建筑的坐标
	 * @param player 玩家
	 * @param buildingType 建筑类型
	 * @return
	 */
	public List<Integer> getBuildingPosition(IPlayer player, Integer buildingType);
	
	/**
	 * 获取建筑的坐标
	 * @param player 玩家
	 * @param buildingType 建筑类型
	 * @param level 大于等于这个等级
	 * @return
	 */
	public List<Integer> getBuildingPosition(IPlayer player, Integer buildingType, Integer level);
	
	/**
	 * 无效的区域
	 * @return
	 */
	public List<Integer> getDisableList();
	
	/**
	 * 空位置
	 * @return
	 */
	public List<Integer> getEmptyList();
	
	/**
	 * 设置地形
	 * @param landformMap 地形数据
	 */
	public void setLandformMap(Map<Integer, Integer> landformMap);
	
	/**
	 * 
	 * @return 地形数据
	 */
	public Map<Integer, Integer> getLandformMap();
	
	/**
	 * 地图上的物品
	 * @return
	 */
	public Map<Integer, ITreasure> getTreasureMap();
	
	/**
	 * 获取所有建筑物
	 * @return
	 */
	public List<IBuilding> getBuildingList();
	
	/**
	 * 获取建筑物
	 * @param player 玩家
	 * @return
	 */
	public List<IBuilding> getBuildingList(IPlayer player);
	
	/**
	 * 获取建筑物
	 * @param type 建筑物类型
	 * @param player 玩家
	 * @return
	 */
	public List<IBuilding> getBuildingList(IPlayer player, Integer type);
	
	/**
	 * 根据坐标查找建筑
	 * @param position
	 * @return
	 */
	public IBuilding getBuilding(Integer position);
	
	/**
	 * 增加一个建筑物
	 * @param position
	 * @param building
	 */
	public void addBuilding(IBuilding building);
	
	/**
	 * 占领建筑
	 * @param position 位置
	 * @param player 玩家
	 */
	public void captureBuilding(Integer position, IPlayer player);
	
	/**
	 * 在地图上增加一块区域（地图是由若干区域组成）
	 * @param place
	 */
	public void addPlace(IPlace place);
	
	public Integer getXBorder();
	
	public Integer getYBorder();
	
	/**
	 * 以JSON格式，把map信息发送给前台
	 */
	public void loadMap();
	
	/**
	 * 查询卡片操作范围
	 * @param card
	 * @param action
	 * @return
	 */
	public List<Integer> queryRange(ICard card, String action);
	
	/**
	 * 查询技能使用范围，现将查询逻辑交给ActiveSkill来完成
	 * @param skill
	 * @param action
	 * @return
	 */
	public List<Integer> queryRange(ISkill skill, String action);
	
	/**
	 * 查询魔法卡使用范围
	 * @param magic
	 * @param life
	 * @param action
	 * @return
	 */
	public List<Integer> queryRange(MagicCard magic, String action);
	
	/**
	 * 查询选项使用范围
	 * @param option
	 * @param action
	 * @return
	 */
	public List<Integer> queryRange(IOption option, String action);
	
	/**
	 * 移动到指定位置
	 * @param life 生物
	 * @param position 指定位置
	 * @param type 移动类型
	 */
	public List<Integer> move(LifeCard life, Integer position, Integer type);
	
	public static final Integer Relative_Top = 0;
	public static final Integer Relative_LeftTop = 10;
	public static final Integer Relative_Left = 9;
	public static final Integer Relative_LeftBottom = 8;
	public static final Integer Relative_Bottom = 6;
	public static final Integer Relative_RightTop = 2;
	public static final Integer Relative_Right = 3;
	public static final Integer Relative_RightBottom = 4;
	
	
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
	 * 获取某玩家战场上所有生物
	 * @param player
	 * @return
	 */
	public List<LifeCard> list(IPlayer player, Integer status);
	
	/**
	 * 获取战场上所有生物
	 * @param player
	 * @return
	 */
	public List<LifeCard> list(Integer status);
	
	/**
	 * 获取指定范围内的随从
	 * @param stand
	 * @param step
	 * @param type
	 * @return
	 */
	public List<LifeCard> list(Integer stand, Integer step, Integer type);
	
	/**
	 * 从起点到终点的最短路径，根据移动范围来获取路径上的那个点
	 * @param stand 当前位置
	 * @param dest 目标位置
	 * @param step 步数
	 * @param moveType 移动类型
	 * @return
	 */
	public Integer getPointByWay(Integer stand, Integer dest, Integer step, Integer moveType);
	
	/**
	 * 采用String格式来表示中立生物的信息："ID,位置,数量,策略"
	 * @return
	 */
	public List<String> getNpcData();
	
	/**
	 * 中立部队
	 * @return
	 */
	public IPlayer getNeutral();

}
