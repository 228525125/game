package org.cx.game.widget;

import org.cx.game.core.IPlayer;

public class Building implements IBuilding {

	private IPlace place = null;
	private IPlayer player = null;
	private Integer type = 0;
	
	public Building(Integer type) {
		// TODO Auto-generated constructor stub
		this.type = type;
	}
	
	@Override
	public IPlace getOwner() {
		// TODO Auto-generated method stub
		return place;
	}

	@Override
	public void setOwner(IPlace place) {
		// TODO Auto-generated method stub
		this.place = place;
	}

	@Override
	public IPlayer getBuildingPlayer() {
		// TODO Auto-generated method stub
		return this.player;
	}

	@Override
	public void setBuildingPlayer(IPlayer player) {
		// TODO Auto-generated method stub
		this.player = player;
	}

	@Override
	public Integer getType() {
		// TODO Auto-generated method stub
		return this.type;
	}

}
