package org.cx.game.widget.building;

import java.util.List;

public class Town extends Building implements IBuilding {

	private Integer level = 1;
	private Boolean main = false; 
	
	public Town(Integer position, Boolean main) {
		// TODO Auto-generated constructor stub
		super(IBuilding.TOWN, position);
		this.main = main;
	}

	public Integer getLevel() {
		// TODO Auto-generated method stub
		return this.level;
	}

	public void setLevel(Integer level) {
		// TODO Auto-generated method stub
		this.level = level;
	}
	
	public Boolean isMain() {
		// TODO Auto-generated method stub
		return this.main;
	}
	
	/**
	 * 该方法用于反射机制
	 */
	public void setOptions(List<IOption> options) {
		// TODO Auto-generated method stub
		super.setOptions(options);
	}
}
