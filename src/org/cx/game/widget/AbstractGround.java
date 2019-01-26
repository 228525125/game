package org.cx.game.widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.cx.game.corps.AbstractCorps;
import org.cx.game.tools.SpaceArithmetic;
import org.cx.game.widget.building.AbstractBuilding;
import org.cx.game.widget.treasure.Treasure;

/**
 * 战场，它是场景（Scene）和地带（Zone）的基类，因此它的所以功能都是通用的，
 * 这也限制了它的一些功能，例如，它没有墓地的概念，所以removeCorps方法都是
 * 对place.corps而言；
 * Ground的所有方法都要进行越界判断，以保证不会出现越界的点；当取一个越界的
 * 点时，返回null；注意：SpaceArithmetic不考虑越界问题；
 * @author chenxian
 *
 */
public abstract class AbstractGround {

	/**
	 * 边界上
	 */
	public static final Integer Equal = 0;
	
	/**
	 * 范围内
	 */
	public static final Integer Contain = 1;
	
	//public static final Integer Relative_Top = 0;
	public static final Integer Relative_LeftTop = 10;
	public static final Integer Relative_Left = 9;
	public static final Integer Relative_LeftBottom = 8;
	//public static final Integer Relative_Bottom = 6;
	public static final Integer Relative_RightTop = 2;
	public static final Integer Relative_Right = 3;
	public static final Integer Relative_RightBottom = 4;
	
	private Integer id = null;
	private String name = "";
	private Integer xBorder = 0;                                       //边界x轴长度
	private Integer yBorder = 0;                                       //边界y轴长度
	
	private AbstractArea area = null;
	
	private List<AbstractCorps> corpsList = new ArrayList<AbstractCorps>();
	//private List<AbstractCorps> deadCorpsList = new ArrayList<AbstractCorps>();
	private Map<Integer,AbstractPlace> ground = new HashMap<Integer,AbstractPlace>();
	private List<AbstractBuilding> buildingList = new ArrayList<AbstractBuilding>();                 //建筑
	private Map<Integer, Integer> landformMap = new HashMap<Integer, Integer>();
	private List<Integer> troopList = new ArrayList<Integer>();         //阵营
	private Map<Integer, Integer> entranceMap = new HashMap<Integer, Integer>();  //入口  坐标 - 阵营
	
	private Map<Integer, Treasure> treasureMap = new HashMap<Integer, Treasure>();
	private List<Treasure> treasureList = new ArrayList<Treasure>();
	
	public AbstractGround(Integer id, String name, Integer xBorder, Integer yBorder) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.name = name;
		this.xBorder = xBorder;
		this.yBorder = yBorder;
	}
	
	public Integer getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
	
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	/**
	 * 战场x边界
	 * @return
	 */
	public Integer getXBorder() {
		// TODO Auto-generated method stub
		return this.xBorder;
	}

	/**
	 * 战场y边界
	 * @return
	 */
	public Integer getYBorder() {
		// TODO Auto-generated method stub
		return this.yBorder;
	}
	
	/**
	 * 控制列表，根据战场类型的不同，控制列表也不同；
	 * @return
	 */
	public abstract AbstractControlQueue getQueue();
	
	//-------------------- Corps ---------------------

	/**
	 * 在指定位置放置一个corps，用于初始化ground
	 * @param position 位置
	 * @param corps
	 */
	public void placementCorps(Integer position, AbstractCorps corps) {
		// TODO Auto-generated method stub
		this.corpsList.add(corps);
		
		AbstractPlace place = getPlace(position);
		place.in(corps);
	}
	
	/**
	 * 移除corps，从ground中彻底移除
	 * @param corps
	 */
	public Boolean removeCorps(AbstractCorps corps) {
		// TODO Auto-generated method stub
		Boolean ret = this.corpsList.remove(corps);
		
		if(ret){
			Integer position = corps.getPosition();
			AbstractPlace place = getPlace(position);
			place.out(corps);
		}
		
		return ret;
	}

	/**
	 * 根据位置查找corps，不包括墓地，注意这里只返回一个corps
	 * @param position
	 * @return 
	 */
	public AbstractCorps getCorps(Integer position) {
		// TODO Auto-generated method stub
		if(null!=ground.get(position))
			return ground.get(position).getCorps();
		else
			return null;
	}
	
	/**
	 * 战场上的corps，不要试图通过getLivingCorpsList()调用方法来改变this.livingCorpsList；
	 * @return
	 */
	public List<AbstractCorps> getCorpsList() {
		// TODO Auto-generated method stub
		List<AbstractCorps> retList = new ArrayList<AbstractCorps>();
		retList.addAll(this.corpsList);
		return retList;
	}

	/**
	 * 只能查找战场上的生物位置，包含墓地
	 */
	public Integer getPosition(AbstractCorps corps) {
		// TODO Auto-generated method stub
		for(Entry<Integer, AbstractPlace> entry : ground.entrySet()){
			if(entry.getValue().contains(corps)){
				return entry.getKey();
			}else if(entry.getValue().getCemetery().contains(corps)){
				return entry.getKey();
			}
		}
		return null;
	}
	
	//-------------------- Area -----------------------------
	
	/**
	 * 所属区域
	 * @return
	 */
	public AbstractArea getArea() {
		return this.area;
	}
	
	public void setArea(AbstractArea area) {
		this.area = area;
	}
	
	public void setTroopList(List<Integer> troopList) {
		this.troopList = troopList;
	}
	
	//-------------------- Troop -------------------------
	
	/**
	 * 阵营
	 * @return 
	 */
	public List<Integer> getTroopList() {
		return this.troopList;
	}
	
	/**
	 * 入口，阵营 - 坐标
	 * @return
	 */
	public Map<Integer, Integer> getEntranceMap() {
		return entranceMap;
	}

	public void setEntranceMap(Map<Integer, Integer> entranceMap) {
		this.entranceMap = entranceMap;
	}
	
	/**
	 * 根据阵营查询入口
	 * @param troop 
	 * @return
	 */
	public List<Integer> getEntranceList(Integer troop) {
		List<Integer> retList = new ArrayList<Integer>();
		for(Integer position : getEntranceMap().keySet()){
			if(troop.equals(getEntranceMap().get(position)))
				retList.add(position);
		}
		
		return retList;				
	}
	
	//-------------------- Building -------------------------

	/**
	 * 放置一个建筑物到战场
	 * @param position
	 * @param building
	 */
	public void placementBuilding(Integer position, AbstractBuilding building) {
		// TODO Auto-generated method stub		
		AbstractPlace place = getPlace(position);		
		place.setBuilding(building);
		
		buildingList.add(building);
	}

	public List<AbstractBuilding> getBuildingList() {
		return buildingList;
	}
	
	//--------------------- Landform -------------------------
	
	/**
	 * 将坐标转换为Place
	 * @param position 坐标
	 * @return
	 */
	public AbstractPlace getPlace(Integer position) {
		// TODO Auto-generated method stub
		return ground.get(position);
	}

	/**
	 * 在地图上增加一块区域（地图是由若干区域组成）
	 * @param place
	 */
	public void addPlace(AbstractPlace place) {
		// TODO Auto-generated method stub
		ground.put(place.getPosition(), place);
	}

	/**
	 * 设置地形
	 * @param landformMap 地形数据
	 */
	public void setLandformMap(Map<Integer, Integer> landformMap) {
		// TODO Auto-generated method stub
		this.landformMap = landformMap;
		
		for(Entry<Integer,Integer> entry : landformMap.entrySet()){
			Integer pos = entry.getKey();
			AbstractPlace place = getPlace(pos);
			place.setLandform(entry.getValue());
		}
	}

	/**
	 * 
	 * @return 地形数据
	 */
	public Map<Integer, Integer> getLandformMap() {
		// TODO Auto-generated method stub
		Map<Integer, Integer> retMap = new HashMap<Integer, Integer>();
		retMap.putAll(this.landformMap);
		return retMap;
	}
	
	//-------------------------- Treasure ---------------------------
	
	/**
	 * 初始地图时，放置物品
	 * @param treasureMap
	 */
	public void setTreasureMap(Map<Integer, Treasure> treasureMap) {
		this.treasureMap = treasureMap;
		
		for(Entry<Integer, Treasure> entry : treasureMap.entrySet()){
			Integer position = entry.getKey();
			AbstractPlace place = getPlace(position);
			Treasure treasure = entry.getValue();
			place.setTreasure(treasure);
			this.treasureList.add(treasure);
		}
	}

	Map<Integer, Treasure> getTreasureMap() {
		return treasureMap;
	}
	
	/**
	 * 地图上的物品
	 * @return
	 */
	public List<Treasure> getTreasureList() {
		// TODO Auto-generated method stub
		return this.treasureList;
	}

	/**
	 * 移除物品
	 * @param treasure
	 */
	public void removeTreasure(Treasure treasure) {
		// TODO Auto-generated method stub
		AbstractPlace place = getPlace(treasure.getPosition());
		place.setTreasure(null);
		this.treasureMap.remove(treasure);
		this.treasureList.remove(treasure);
	}
	
	/**
	 * 判断是否超出边界
	 * @param position
	 * @return
	 */
	protected Boolean isOver(Integer position){
		Integer [] ps = SpaceArithmetic.integerToPoint(position);
		if(ps[0]<1||ps[0]>getXBorder())
			return true;
		if(ps[1]<1||ps[1]>getYBorder())
			return true;
		return false;
	}

	/**
	 * 两个坐标之间的最短距离，不考虑其它因素
	 * @param start
	 * @param stop
	 * @return
	 */
	public abstract Integer distance(Integer start, Integer stop);
	
	/**
	 * 两个坐标之间的最短距离，考虑地形
	 * @param start
	 * @param stop
	 * @param moveType 移动类型
	 * @return
	 */
	public abstract Integer distance(Integer start, Integer stop, Integer moveType);
	
	/**
	 * 获取指定距离的区域，不考虑其它因素
	 * @param position
	 * @param step
	 * @param type
	 * @return
	 */
	public abstract List<Integer> areaForDistance(Integer position, Integer step, Integer type);
	
	/**
	 * 获取指定距离的区域，不考虑其它因素
	 * @param position
	 * @param range 与数字不同，这里用字符串表示例如：3-8；
	 * @param type
	 * @return
	 */
	public abstract List<Integer> areaForDistance(Integer position, String range, Integer type);
	
	/**
	 * 获取指定距离的区域，考虑障碍物，加载地形
	 * @param position 指定坐标
	 * @param step 距离，注意step必须大于0，否则无意义
	 * @param type  0:刚好在边界上;1范围内;2范围外;
	 * @param moveType  移动类型
	 * @return
	 */
	public abstract List<Integer> areaForDistance(Integer position, Integer step, Integer type, Integer moveType);
	
	/**
	 * 相对与目标位置的方向，stand与target位置必须是相邻的
	 * @param stand 站位
	 * @param target 目标位置
	 * @return 方向
	 */
	public abstract Integer getDirection(Integer stand, Integer target);
	
	/**
	 * 根据方向查询相邻位置
	 * @param stand 站位
	 * @param Direction 方向
	 * @return 位置，如果越界则返回null
	 */
	public abstract Integer getPosition(Integer stand, Integer direction);
	
	/**
	 * 两侧的位置，stand与target位置必须是相邻的
	 * @param stand 站位
	 * @param direction 方向
	 * @return
	 */
	public abstract List<Integer> twoFlanks(Integer stand, Integer direction);
	
	/**
	 * 从起点到终点的最短路径，根据移动范围来获取路径上的那个点
	 * @param stand 当前位置
	 * @param dest 目标位置
	 * @param step 步数
	 * @param moveType 移动类型
	 * @param control 敌我状态
	 * @return
	 */
	public abstract Integer getPointByWay(Integer stand, Integer dest, Integer step, Integer moveType);

}
