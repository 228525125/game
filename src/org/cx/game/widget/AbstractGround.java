package org.cx.game.widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.cx.game.core.IPlayer;
import org.cx.game.corps.AbstractCorps;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.tools.CellularDistrict;
import org.cx.game.widget.building.IBuilding;
import org.cx.game.widget.treasure.ITreasure;

public abstract class AbstractGround implements IGround {

	
	private Integer id = null;
	private String name = "";
	private Integer xBorder = 0;                                       //边界x轴长度
	private Integer yBorder = 0;                                       //边界y轴长度
	
	private List<AbstractCorps> livingCorpsList = new ArrayList<AbstractCorps>();
	private List<AbstractCorps> deadCorpsList = new ArrayList<AbstractCorps>();
	private Map<Integer,AbstractPlace> ground = new HashMap<Integer,AbstractPlace>();
	private List<IBuilding> buildingList = new ArrayList<IBuilding>();                 //建筑
	private Map<Integer, Integer> landformMap = new HashMap<Integer, Integer>();
	
	private Map<Integer, ITreasure> treasureMap = new HashMap<Integer, ITreasure>();
	private List<ITreasure> treasureList = new ArrayList<ITreasure>();
	
	public AbstractGround(Integer id, String name, Integer xBorder, Integer yBorder) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.name = name;
		this.xBorder = xBorder;
		this.yBorder = yBorder;
	}
	
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public Integer getXBorder() {
		// TODO Auto-generated method stub
		return this.xBorder;
	}

	@Override
	public Integer getYBorder() {
		// TODO Auto-generated method stub
		return this.yBorder;
	}
	
	//-------------------- Corps ---------------------

	@Override
	public void placementCorps(Integer position, AbstractCorps corps) {
		// TODO Auto-generated method stub
		this.livingCorpsList.add(corps);
		
		AbstractPlace place = getPlace(position);
		place.in(corps);
		
		corps.setGround(this);
	}
	
	@Override
	public void removeCorps(AbstractCorps corps) {
		// TODO Auto-generated method stub
		AbstractPlace place = getPlace(corps.getPosition());
		place.out();
		
		this.livingCorpsList.remove(corps);
	}

	@Override
	public AbstractCorps getCorps(Integer position) {
		// TODO Auto-generated method stub
		if(null!=ground.get(position))
			return ground.get(position).getCorps();
		else
			return null;
	}

	@Override
	public Boolean contains(AbstractCorps corps) {
		// TODO Auto-generated method stub
		return ground.containsValue(corps);
	}
	
	@Override
	public List<AbstractCorps> getLivingCorpsList() {
		// TODO Auto-generated method stub
		return this.livingCorpsList;
	}
	
	@Override
	public List<AbstractCorps> getDeadCorpsList() {
		// TODO Auto-generated method stub
		return this.deadCorpsList;
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
	
	@Override
	public void inCemetery(AbstractCorps corps) {
		// TODO Auto-generated method stub
		AbstractPlace place = getPlace(corps.getPosition());
		place.out();
		place.inCemetery(corps);
		
		this.livingCorpsList.remove(corps);
		this.deadCorpsList.add(corps);
	}
	
	@Override
	public void outCemetery(AbstractCorps corps) {
		// TODO Auto-generated method stub
		AbstractPlace place = getPlace(corps.getPosition());
		place.outCemetery(corps);
		
		this.deadCorpsList.remove(corps);
	}
	
	//-------------------- Corps End ------------------------
	
	//-------------------- Building -------------------------

	@Override
	public void placementBuilding(Integer position, IBuilding building) {
		// TODO Auto-generated method stub		
		AbstractPlace place = getPlace(position);		
		place.setBuilding(building);
		
		buildingList.add(building);
	}

	@Override
	public List<IBuilding> getBuildingList() {
		return buildingList;
	}
	
	//--------------------- Building End ---------------------
	
	//--------------------- Landform -------------------------

	@Override
	public AbstractPlace getPlace(Integer position) {
		// TODO Auto-generated method stub
		return ground.get(position);
	}

	@Override
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

	@Override
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
	public void setTreasureMap(Map<Integer, ITreasure> treasureMap) {
		this.treasureMap = treasureMap;
		
		for(Entry<Integer, ITreasure> entry : treasureMap.entrySet()){
			Integer position = entry.getKey();
			AbstractPlace place = getPlace(position);
			ITreasure treasure = entry.getValue();
			place.setTreasure(treasure);
			this.treasureList.add(treasure);
		}
	}

	Map<Integer, ITreasure> getTreasureMap() {
		return treasureMap;
	}
	
	@Override
	public List<ITreasure> getTreasureList() {
		// TODO Auto-generated method stub
		return this.treasureList;
	}

	@Override
	public void removeTreasure(ITreasure treasure) {
		// TODO Auto-generated method stub
		AbstractPlace place = getPlace(treasure.getPosition());
		place.setTreasure(null);
		this.treasureMap.remove(treasure);
		this.treasureList.remove(treasure);
	}
	
	//--------------------------- Treasure End ----------------------------

	abstract public Integer distance(Integer start, Integer stop);
	abstract public Integer distance(Integer start, Integer stop, Integer moveType, IPlayer control);
	abstract public List<Integer> areaForDistance(Integer position, Integer step, Integer type, Integer moveType, IPlayer control);
	abstract public List<Integer> areaForDistance(Integer position, Integer step, Integer type);
	abstract public Integer getDirection(Integer stand, Integer target);
	abstract public Integer getPosition(Integer stand, Integer direction);
	abstract public List<Integer> twoFlanks(Integer stand, Integer direction);
	abstract public Integer getPointByWay(Integer stand, Integer dest, Integer step, Integer moveType, IPlayer control);
	
	/*@Override
	public Boolean removeCorps(AbstractCorps corps) throws RuleValidatorException {
		// TODO Auto-generated method stub
		Boolean ret = this.corpsList.remove(corps);
		
		if(ret){
			Integer position = corps.getPosition();
			AbstractPlace place = getPlace(position);
			place.out();
			corps.setGround(null);
		}
		
		return ret;
	}*/
	
	/*@Override
	public List<AbstractCorps> getCorpsList() {
		// TODO Auto-generated method stub
		List<AbstractCorps> corpsList = new ArrayList<>
		return this.corpsList;
	}*/

}
