package org.cx.game.widget;

import org.cx.game.core.IPlayer;

public class Town extends Building implements ITown {

	private Integer position = 0;
	private Integer level = 1;
	private Boolean main = false; 
	private IPlayer player = null;
	
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
	
	@Override
	public IPlayer getOccupier() {
		// TODO Auto-generated method stub
		return this.player;
	}

	@Override
	public void setOccupier(IPlayer player) {
		// TODO Auto-generated method stub
		this.player = player;
	}
	
}
