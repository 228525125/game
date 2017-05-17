package org.cx.game.widget;

public class Town extends Building implements ITown {

	private Integer position = 0;
	private Integer level = 1;
	private Boolean main = false; 
	
	
	public Town(Integer position, Boolean main) {
		// TODO Auto-generated constructor stub
		super(IBuilding.TOWN);
		this.position = position;
		this.main = main;
	}
	
	@Override
	public Integer getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public Integer getLevel() {
		// TODO Auto-generated method stub
		return this.level;
	}

	@Override
	public void setLevel(Integer level) {
		// TODO Auto-generated method stub
		this.level = level;
	}
	
	@Override
	public Boolean isMain() {
		// TODO Auto-generated method stub
		return this.main;
	}
	
}
