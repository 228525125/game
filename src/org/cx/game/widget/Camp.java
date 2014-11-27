package org.cx.game.widget;

import org.cx.game.core.IPlayer;

public class Camp implements ICamp {

	private Integer life = 8000;
	private Integer def = 0;
	private IPlace place = null;
	private IPlayer player = null;
	
	public Camp(Integer life, Integer def, IPlace place) {
		// TODO Auto-generated constructor stub
		this.life = life;
		this.def = def;
		this.place = place;
		this.place.setCamp(this);
		this.place.setDisable(true);
	}
	
	@Override
	public Integer getDef() {
		// TODO Auto-generated method stub
		return def;
	}

	@Override
	public Integer getLife() {
		// TODO Auto-generated method stub
		return life;
	}

	@Override
	public IPlace getOwner() {
		// TODO Auto-generated method stub
		return place;
	}

	public IPlayer getPlayer() {
		return player;
	}
	
	public void setPlayer(IPlayer player){
		this.player = player;
	}

}
