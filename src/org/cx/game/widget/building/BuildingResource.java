package org.cx.game.widget.building;

import java.util.HashMap;
import java.util.Map;

public class BuildingResource extends Building {
	
	private Map<String,Integer> resource = new HashMap<String,Integer>();
	
	public BuildingResource(Integer buildingType) {
		super(buildingType);
		// TODO Auto-generated constructor stub
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
