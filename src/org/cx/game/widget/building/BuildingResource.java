package org.cx.game.widget.building;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.core.IPlayer;

public class BuildingResource extends Building {
	
	private Map<String,Integer> resource = new HashMap<String,Integer>();
	
	public BuildingResource(Integer buildingType) {
		super(buildingType);
		// TODO Auto-generated constructor stub
		
		this.resource.put(IPlayer.Gold, 0);
		this.resource.put(IPlayer.Wood, 0);
		this.resource.put(IPlayer.Stone, 0);
		this.resource.put(IPlayer.Ore, 0);
	}

	public Map<String,Integer> getResource() {
		return resource;
	}

	public void setResource(Map<String,Integer> resource) {
		this.resource = resource;
	}

	/**
	 * 产出
	 */
	public void output() {
		getPlayer().addToResource(this.resource);
	}
	
	@Override
	public void setConsume(Map<String, Integer> consume) {
		// TODO Auto-generated method stub
		super.setConsume(consume);
	}
	
	@Override
	public void setBuildWait(Integer bout) {
		// TODO Auto-generated method stub
		super.setBuildWait(bout);
	}
	
	@Override
	public void setLevelLimit(Integer levelLimit) {
		// TODO Auto-generated method stub
		super.setLevelLimit(levelLimit);
	}

}
