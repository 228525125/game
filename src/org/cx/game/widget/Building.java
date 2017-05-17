package org.cx.game.widget;

public class Building implements IBuilding {

	private IPlace place = null;
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
	public Integer getType() {
		// TODO Auto-generated method stub
		return this.type;
	}

}
