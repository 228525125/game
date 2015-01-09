package org.cx.game.widget;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.IActiveSkill;
import org.cx.game.card.skill.ISkill;
import org.cx.game.core.IPlayer;

/**
 * position用二维坐标系表示，中间用0000隔开，例如(1,2) = 100002
 * @author jiuhuan
 *
 */
public interface IGround extends IContainer{
	
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
	
	/**
	 * 欧氏距离算法，即允许走斜线，不考虑障碍物
	 * @param start
	 * @param stop
	 * @return
	 */
	public Integer easyOuDistance(Integer start, Integer stop);
	
	/**
	 * 两个坐标之间的最短距离，不考虑障碍物，并且允许斜线方向计算
	 * @param start
	 * @param stop
	 * @return
	 */
	public Integer easyDistanceDiagonal(Integer start, Integer stop);
	
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
	 * 获取指定距离的区域，不考虑障碍物，并且允许斜线方向计算
	 * @param position
	 * @param step
	 * @param type
	 * @return
	 */
	public List<Integer> easyAreaForDistanceDiagonal(Integer position, Integer step, Integer type);
	
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
	 * 获取营地的坐标
	 * @return
	 */
	public List<Integer> getCampPosition(IPlayer player);
	
	/**
	 * 增加一个营地
	 * @param position
	 * @param camp
	 */
	public void addCamp(ICamp camp);
	
	/**
	 * 在地图上增加一块区域（地图是由若干区域组成）
	 * @param place
	 */
	public void addPlace(IPlace place);
	
	public Integer getXBorder();
	
	public Integer getYBorder();
	
	/**
	 * 查询卡片操作范围
	 * @param card
	 * @param action
	 * @return
	 */
	public List<Integer> queryRange(ICard card, String action);
	
	/**
	 * 查询技能使用范围
	 * @param skill
	 * @param action
	 * @return
	 */
	public List<Integer> queryRange(ISkill skill, String action);
	
	/**
	 * 移动到指定位置
	 * @param life 生物
	 * @param position 指定位置
	 * @param type 移动类型
	 */
	public void move(LifeCard life, Integer position, Integer type);
	
	/**
	 * 查询stand相对于target站位的阴影部分
	 * @param stand
	 * @param target
	 * @param range 范围
	 * @return
	 */
	public List<Integer> arc(Integer stand, Integer direction, Integer range);
	
	public static final Integer Relative_Top = 0;
	public static final Integer Relative_Left = 1;
	public static final Integer Relative_Bottom = 2;
	public static final Integer Relative_Right = 3;
	
	/**
	 * 相对与目标位置，是上下左右其中一种，stand与target位置必须是相邻的
	 * @param stand 站位
	 * @param target 目标位置
	 * @return
	 */
	public Integer queryDirection(Integer stand, Integer target);
	
	/**
	 * 两侧的位置，stand与target位置必须是相邻的
	 * @param stand 站位
	 * @param direction 方向
	 * @return
	 */
	public List<Integer> twoFlanks(Integer stand, Integer direction); 
	
	/**
	 * 以target为基点，获取direction方向上的一条直线（不包含站位）
	 * @param stand 站位
	 * @param direction 方向
	 * @return
	 */
	public List<Integer> line(Integer stand, Integer direction, Integer range);

}
