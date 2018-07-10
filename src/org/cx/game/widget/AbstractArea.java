package org.cx.game.widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cx.game.widget.building.AbstractBuilding;

public abstract class AbstractArea {
	
	private Integer id = null;
	private String imagePath = "";
	private Integer startingPoint = null;
	
	private List<AbstractBuilding> spatialBuildingList = new ArrayList<AbstractBuilding>();
	private List<AbstractGround> groundList = new ArrayList<AbstractGround>();
	private Map<Integer, AbstractGround> idGroundMap = new HashMap<Integer, AbstractGround>();
	private List<Integer> troopList = new ArrayList<Integer>();         //阵营
	
	public AbstractArea(Integer id, String imagePath) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.imagePath = imagePath;
	}
	
	/**
	 * 该方法在对象被创建后调用
	 */
	public abstract void afterConstruct();
	
	public AbstractGround getGround(Integer mapId) {
		return idGroundMap.get(mapId);
	}
	
	public List<AbstractGround> getGroundList() {
		return groundList;
	}
	
	public void setAtlas(List<Integer> atlas) {
		for(Integer mapId : atlas){
			AbstractGround ground = GroundFactory.getInstance(mapId);
			this.groundList.add(ground);
			this.idGroundMap.put(mapId, ground);
			
			ground.setArea(this);
			
			
		}
	}
	
	public void addSpatialBuilding(AbstractBuilding spatialBuilding) {
		this.spatialBuildingList.add(spatialBuilding);
	}
	
	public List<AbstractBuilding> getSpatialBuildingList() {
		return this.spatialBuildingList;
	}
	
	public AbstractBuilding getSpatialBuilding(Integer typeId) {
		for(AbstractBuilding building : getSpatialBuildingList()){
			if(typeId.equals(building.getType()))
				return building;
		}
		return null;
	}
	
	public List<Integer> getTroopList() {
		return this.troopList;
	}
	
	public void setTroopList(List<Integer> troopList) {
		this.troopList = troopList;
	}
	
	public void setStartingPoint(Integer mapId) {
		this.startingPoint = mapId;
	}
	
	public AbstractGround getStartingPoint() {
		return getGround(this.startingPoint);
	}
}
