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

/**
 * position用二维坐标系表示，中间用0000隔开，例如(1,2) = 100002
 * @author jiuhuan
 *
 */
public interface IGround extends IContainer{
	
	public static String space = "8008";                               //位置坐标间隔符
	
	public LifeCard getCard(Integer position);
	
	/**
	 * 两个坐标之间的最短距离，考虑障碍物
	 * @param start 起始坐标
	 * @param stop 终止坐标
	 * @return
	 */
	public Integer distance(Integer start, Integer stop);
	
	/**
	 * 两个坐标之间的最短距离，不考虑障碍物
	 * @param start
	 * @param stop
	 * @return
	 */
	public Integer easyDistance(Integer start, Integer stop);
	
	public static final Integer Equal = 0;
	public static final Integer Contain = 1;
	
	/**
	 * 获取指定距离的区域，考虑障碍物
	 * @param position 指定坐标
	 * @param step 距离，注意step必须大于0，否则无意义
	 * @param type  0:刚好在边界上;1范围内;2范围外;
	 * @return
	 */
	public List<Integer> areaForDistance(Integer position, Integer step, Integer type);
	
	public final static Integer Shade_Range = 5;
	
	/**
	 * 获取指定距离的区域，不考虑障碍物
	 * @param position
	 * @param step
	 * @param type
	 * @return
	 */
	public List<Integer> easyAreaForDistance(Integer position, Integer step, Integer type);
	
	/**
	 * 获得两点之间的最短路线，考虑障碍物，并且start<>stop
	 * @param start
	 * @param stop
	 * @return
	 */
	public List<Integer> route(Integer start, Integer stop);
	
	/**
	 * 获得两点之间的最短路线，不考虑障碍物
	 * @param start
	 * @param stop
	 * @return
	 */
	public List<Integer> easyRoute(Integer start, Integer stop);
	
	/**
	 * 根据坐标
	 * @param position 坐标
	 * @return
	 */
	public IPlace getPlace(Integer position);
	
	/**
	 * 获取城镇的坐标
	 * @return
	 */
	public List<Integer> getTownPosition(IPlayer player);
	
	/**
	 * 获取城镇的坐标
	 * @param player 玩家
	 * @param level 大于等于这个等级
	 * @return
	 */
	public List<Integer> getTownPosition(IPlayer player, Integer level);
	
	/**
	 * 获取主城镇的坐标
	 * @param player 玩家 
	 * @return
	 */
	public Integer getMainTownPosition(IPlayer player);
	
	/**
	 * 获得一个随机的入口 （改）
	 * @return
	 */
	public Integer getRandomEntry(ITown town, LifeCard life);
	
	/**
	 * 获取入口位置 （改）
	 * @return
	 */
	public List<Integer> getEntryList(LifeCard life);
	
	/**
	 * 受地形限制的区域
	 * @return
	 */
	public List<Integer> getDisableList();
	
	/**
	 * 获取营地
	 * @return
	 */
	public List<ITown> getTownList();
	
	/**
	 * 增加一个城镇
	 * @param position
	 * @param town
	 */
	public void addTown(ITown town);
	
	/**
	 * 指定一个玩家的主城
	 * @param townID 主城ID
	 * @param player 玩家
	 */
	public void setPlayerToTown(Integer townID, IPlayer player);
	
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
	 * 据点
	 * @return
	 */
	public List<IStrongHold> getStrongHoldList();
	
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
	public List<LifeCard> list(IPlayer player);
	
	/**
	 * 获取指定范围内的随从
	 * @param stand
	 * @param step
	 * @param type
	 * @return
	 */
	public List<LifeCard> list(Integer stand, Integer step, Integer type);

}
