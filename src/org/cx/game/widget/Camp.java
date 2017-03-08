package org.cx.game.widget;

import org.cx.game.core.IPlayer;

public class Camp implements ICamp {

	private Integer position = 0;
	private IPlace place = null;
	private IPlayer player = null;
	
	public Camp(Integer position) {
		// TODO Auto-generated constructor stub
		this.position = position;
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
	
	public IPlayer getCampPlayer() {
		return player;
	}
	
	public void setCampPlayer(IPlayer player){
		this.player = player;
	}

	@Override
	public Integer getPosition() {
		// TODO Auto-generated method stub
		return position;
	}
}
