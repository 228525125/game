package org.cx.game.widget;

import java.util.ArrayList;
import java.util.List;

import org.cx.game.core.IPlayer;

public class Camp implements ICamp {

	private Integer hp = 8000;
	private Double immuneDamageRatio = 1d;
	private Integer position = 0;
	private IPlace place = null;
	private IPlayer player = null;
	private List<Integer> entryList = new ArrayList<Integer>();
	
	public Camp(Integer hp, Double immuneDamageRatio, Integer position) {
		// TODO Auto-generated constructor stub
		this.hp = hp;
		this.immuneDamageRatio = immuneDamageRatio;		
		this.position = position;
	}
	
	@Override
	public Double getImmuneDamageRatio() {
		// TODO Auto-generated method stub
		return immuneDamageRatio;
	}

	@Override
	public Integer getHp() {
		// TODO Auto-generated method stub
		return hp;
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

	@Override
	public void setOwner(IPlace place) {
		// TODO Auto-generated method stub
		this.place = place;
		this.place.setCamp(this);
		this.place.setDisable(true);
	}

	@Override
	public Integer getPosition() {
		// TODO Auto-generated method stub
		return position;
	}
	
	@Override
	public List<Integer> getEntryList() {
		// TODO Auto-generated method stub
		return this.entryList;
	}
	
	@Override
	public void setEntryList(List<Integer> entryList) {
		// TODO Auto-generated method stub
		this.entryList = entryList;
	}
}
