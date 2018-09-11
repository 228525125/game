package org.cx.game.widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.cx.game.core.AbstractPlayer;
import org.cx.game.corps.AbstractCorps;
import org.cx.game.magic.skill.AbstractSkill;
import org.cx.game.tools.CommonIdentifier;
import org.cx.game.widget.building.AbstractBuilding;
import org.cx.game.widget.treasure.Treasure;

public abstract class AbstractGround {

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
	
	private Integer id = null;
	private String name = "";
	private Integer xBorder = 0;                                       //边界x轴长度
	private Integer yBorder = 0;                                       //边界y轴长度
	
	private AbstractArea area = null;
	
	private List<AbstractCorps> livingCorpsList = new ArrayList<AbstractCorps>();
	private List<AbstractCorps> deadCorpsList = new ArrayList<AbstractCorps>();
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
	 * 在指定位置放置一个corps
	 * @param position 位置
	 * @param corps
	 */
	public void placementCorps(Integer position, AbstractCorps corps) {
		// TODO Auto-generated method stub
		this.livingCorpsList.add(corps);
		
		AbstractPlace place = getPlace(position);
		place.in(corps);
		
		corps.setGround(this);
	}
	
	/**
	 * 移除corps
	 * @param corps
	 */
	public Boolean removeCorps(AbstractCorps corps) {
		// TODO Auto-generated method stub
		Boolean ret = this.livingCorpsList.remove(corps);
		
		if(ret){
			Integer position = corps.getPosition();
			AbstractPlace place = getPlace(position);
			place.out();
			
			//corps.setGround(null);  如果只是队伍合并merge，就有问题
		}
		return ret;
	}

	public AbstractCorps getCorps(Integer position) {
		// TODO Auto-generated method stub
		if(null!=ground.get(position))
			return ground.get(position).getCorps();
		else
			return null;
	}

	public Boolean contains(AbstractCorps corps) {
		// TODO Auto-generated method stub
		return ground.containsValue(corps);
	}
	
	/**
	 * 战场上活着的corps
	 * @return
	 */
	public List<AbstractCorps> getLivingCorpsList() {
		// TODO Auto-generated method stub
		return this.livingCorpsList;
	}
	
	/**
	 * 战场上死亡的corps
	 * @return
	 */
	public List<AbstractCorps> getDeadCorpsList() {
		// TODO Auto-generated method stub
		return this.deadCorpsList;
	}
	
	/**
	 * 获取某玩家战场上所有生物
	 * @param player
	 * @return
	 */
	public List<AbstractCorps> getCorpsList(AbstractPlayer player, Integer status) {
		// TODO Auto-generated method stub
		List<AbstractCorps> ret = new ArrayList<AbstractCorps>();
		
		if(CommonIdentifier.Death_Status_Live.equals(status)){
			for(AbstractCorps corps : getLivingCorpsList()){
				if(player.equals(corps.getPlayer()))
					ret.add(corps);
			}
		}
		
		if(CommonIdentifier.Death_Status_Death.equals(status)){
			for(AbstractCorps corps : getDeadCorpsList()){
				if(player.equals(corps.getPlayer()))
					ret.add(corps);
			}
		}
		
		return ret;
	}
	
	/**
	 * 获取战场上所有生物
	 * @param player
	 * @return
	 */
	public List<AbstractCorps> getCorpsList(Integer status) {
		// TODO Auto-generated method stub
		List<AbstractCorps> ret = new ArrayList<AbstractCorps>();
		
		if(CommonIdentifier.Death_Status_Live.equals(status))
			for(AbstractCorps corps : getLivingCorpsList())
				ret.add(corps);
		
		if(CommonIdentifier.Death_Status_Death.equals(status))
			for(AbstractCorps corps : getDeadCorpsList())
				ret.add(corps);
		
		return ret;
	}
	
	/**
	 * 获取指定范围内的随从
	 * @param stand
	 * @param step
	 * @param type
	 * @return
	 */
	public List<AbstractCorps> getCorpsList(Integer stand, Integer step, Integer type) {
		// TODO Auto-generated method stub
		List<AbstractCorps> ls = new ArrayList<AbstractCorps>();
		
		List<Integer> list = areaForDistance(stand, step, type);
		for(Integer position : list){
			AbstractCorps corps = getCorps(position);
			if(null!=corps){
				ls.add(corps);
			}
		}
		
		return ls;
	}

	/**
	 * 只能查找战场上的生物位置，包含墓地
	 */
	public Integer getPosition(AbstractCorps corps) {
		// TODO Auto-generated method stub
		for(Entry<Integer, AbstractPlace> entry : ground.entrySet()){
			if(corps.equals(entry.getValue().getCorps())){
				return entry.getKey();
			}else if(entry.getValue().getCemetery().contains(corps)){
				return entry.getKey();
			}
		}
		return null;
	}
	
	/**
	 * 查询corps操作范围
	 * @param corps
	 * @param action
	 * @return
	 */
	public abstract List<Integer> queryRange(AbstractCorps corps, String action);
	
	/**
	 * 查询技能使用范围，现将查询逻辑交给ActiveSkill来完成
	 * @param skill
	 * @param action
	 * @return
	 
	public abstract List<Integer> queryRange(AbstractSkill skill, String action);*/
	
	/**
	 * 移动到指定位置
	 * @param corps 生物
	 * @param position 指定位置
	 * @param type 移动类型
	 */
	public abstract List<Integer> move(AbstractCorps corps, Integer position, Integer type);
	
	/**
	 * 进入墓地，例如在战场上死亡
	 * @param corps 
	 */
	public void inCemetery(AbstractCorps corps) {
		// TODO Auto-generated method stub
		AbstractPlace place = getPlace(corps.getPosition());
		place.out();
		place.inCemetery(corps);
		
		this.livingCorpsList.remove(corps);
		this.deadCorpsList.add(corps);
	}
	
	/**
	 * 移出墓地，例如英雄复活
	 * @param corps
	 */
	public void outCemetery(AbstractCorps corps) {
		// TODO Auto-generated method stub
		AbstractPlace place = getPlace(corps.getPosition());
		place.outCemetery(corps);
		
		this.deadCorpsList.remove(corps);
	}
	
	//-------------------- Corps End ------------------------
	
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
	
	/**
	 * 获取建筑物
	 * @param player 玩家
	 * @return
	 */
	public List<AbstractBuilding> getBuildingList(AbstractPlayer player) {
		// TODO Auto-generated method stub
		List<AbstractBuilding> list = new ArrayList<AbstractBuilding>();
		for(AbstractBuilding building : getBuildingList()){
			if(null!=building.getPlayer() && player.equals(building.getPlayer()))
				list.add(building);
		}
		return list;
	}
	
	/**
	 * 获取建筑物
	 * @param player 玩家
	 * @param type 建筑物类型
	 * @return
	 */
	public List<AbstractBuilding> getBuildingList(AbstractPlayer player, Integer type) {
		// TODO Auto-generated method stub
		List<AbstractBuilding> list = new ArrayList<AbstractBuilding>();
		for(AbstractBuilding building : getBuildingList(player)){
			if(type.equals(building.getType()))
				list.add(building);
		}
		return list;
	}
	
	/**
	 * 根据建筑物class，进行查询
	 * @param clazz
	 * @return
	 */
	public List<AbstractBuilding> getBuildingList(Class clazz) {
		// TODO Auto-generated method stub
		List<AbstractBuilding> list = new ArrayList<AbstractBuilding>();
		for(AbstractBuilding building : getBuildingList()){
			if(building.getClass().equals(clazz))
				list.add(building);
		}
		return list;
	}
	
	/**
	 * 查询选项使用范围
	 * @param option
	 * @param action
	 * @return
	 */
	public abstract List<Integer> queryRange(AbstractOption option, String action);
	
	//--------------------- Building End ---------------------
	
	//--------------------- Landform -------------------------
	
	/**
	 * 查询Place.corps为null的position的集合
	 * @return 
	 */
	public abstract List<Integer> queryEmptyList();
	
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
		return this.landformMap;
	}
	
	//-------------------------- Landform End -----------------------
	
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
	
	//--------------------------- Treasure End ----------------------------

	/**
	 * 两个坐标之间的最短距离，不考虑地形
	 * @param start
	 * @param stop
	 * @return
	 */
	public abstract Integer distance(Integer start, Integer stop);
	
	/**
	 * 两个坐标之间的最短距离，考虑地形
	 * @param start 必须为起点
	 * @param stop 必须为需要测试的点
	 * @param moveType
	 * @param control 敌我状态
	 * @return 如果stop不可到达，即MAP中为-1，则返回9999
	 */
	public abstract Integer distance(Integer start, Integer stop, Integer moveType, AbstractPlayer control);
	
	/**
	 * 获取指定距离的区域，不考虑障碍物
	 * @param position
	 * @param step
	 * @param type
	 * @return
	 */
	public abstract List<Integer> areaForDistance(Integer position, Integer step, Integer type);
	
	/**
	 * 获取指定距离的区域，考虑障碍物
	 * @param position 指定坐标
	 * @param step 距离，注意step必须大于0，否则无意义
	 * @param type  0:刚好在边界上;1范围内;2范围外;
	 * @param moveType  移动类型
	 * @param control 敌我状态
	 * @return
	 */
	public abstract List<Integer> areaForDistance(Integer position, Integer step, Integer type, Integer moveType, AbstractPlayer control);
	
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
	 * @return
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
	public abstract Integer getPointByWay(Integer stand, Integer dest, Integer step, Integer moveType, AbstractPlayer control);

}
