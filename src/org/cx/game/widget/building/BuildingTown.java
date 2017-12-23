package org.cx.game.widget.building;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.cx.game.action.IUpgrade;
import org.cx.game.core.IPlayer;

/**
 * 有内部建筑物的建筑物
 * @author chenxian
 *
 */
public class BuildingTown extends Building implements IBuilding {
	
	private Map<String, Integer> tax = new HashMap<String, Integer>();
	private List<IBuilding> buildings = new ArrayList<IBuilding>();
	
	public BuildingTown(Integer buildingType) {
		super(buildingType);
		// TODO Auto-generated constructor stub
		setStatus(IBuilding.Building_Status_Complete);  //城镇在地图上已经形成
		
		this.tax.put(IPlayer.Gold, 0);
		this.tax.put(IPlayer.Wood, 0);
		this.tax.put(IPlayer.Stone, 0);
		this.tax.put(IPlayer.Ore, 0);
	}
	
	@Override
	public void setPosition(Integer position) {
		// TODO Auto-generated method stub
		super.setPosition(position);
		
		for(IBuilding building: this.buildings)
			building.setPosition(position);
	}
	
	/**
	 * 征税
	 * @return
	 */
	public Map<String, Integer> getTax() {
		// TODO Auto-generated method stub
		for(IBuilding building : getBuildings()){
			if (building instanceof BuildingResource) {
				BuildingResource br = (BuildingResource) building;
				for(Entry<String,Integer> entry : br.getResource().entrySet()){
					Integer taxValue = this.tax.get(entry.getKey());
					taxValue += entry.getValue();
					this.tax.put(entry.getKey(), taxValue);
				}
			}
		}
		return this.tax;
	}
	
	/**
	 * 内部建筑物
	 * @return
	 */
	public List<IBuilding> getBuildings() {
		// TODO Auto-generated method stub
		return this.buildings;
	}
	
	/**
	 * 内部建筑物的typeID，简化xml
	 * @param list
	 */
	public void setBuildings(List<Integer> buildings) {
		// TODO Auto-generated method stub
		for(Integer typeID : buildings){
			IBuilding building = BuildingFactory.getInstance(typeID);
			building.setOwner(this);
			this.buildings.add(building);
		}
	}
	
	/**
	 * 根据顺序号获取内部建筑
	 * @param index
	 * @return
	 */
	public IBuilding getBuilding(Integer index) {
		// TODO Auto-generated method stub
		return this.buildings.get(index);
	}

	@Override
	public void setPlayer(IPlayer player) {
		// TODO Auto-generated method stub
		super.setPlayer(player);
		
		for(IBuilding building : this.buildings){
			building.setPlayer(player);
		}
	}
}
