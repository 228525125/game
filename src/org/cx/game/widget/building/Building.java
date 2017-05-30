package org.cx.game.widget.building;

import java.util.ArrayList;
import java.util.List;

import org.cx.game.core.IPlayer;
import org.cx.game.tools.I18n;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IPlace;

public abstract class Building implements IBuilding {

	private Integer position = 0;
	private String name = null;
	private IPlace place = null;
	private Integer type = 0;
	private IPlayer player = null;
	private List<IOption> options = new ArrayList<IOption>();
	
	public Building(Integer type, Integer position) {
		// TODO Auto-generated constructor stub
		this.type = type;
		this.position = position;
	}
	
	public String getName() {
		if(null==name)
			name = I18n.getMessage(this, "name");
		return name;
	}
	
	public Integer getPosition() {
		// TODO Auto-generated method stub
		return position;
	}
	
	@Override
	public IPlace getOwner() {
		// TODO Auto-generated method stub
		if(null==this.place){
			IGround ground = getPlayer().getGround();
			this.place = ground.getPlace(getPosition());
		}
		return place;
	}

	@Override
	public void setOwner(IPlace place) {
		// TODO Auto-generated method stub
		this.place = place;
	}
	
	@Override
	public IPlayer getPlayer() {
		// TODO Auto-generated method stub
		return this.player;
	}

	@Override
	public void setPlayer(IPlayer player) {
		// TODO Auto-generated method stub
		this.player = player;
	}

	@Override
	public Integer getType() {
		// TODO Auto-generated method stub
		return this.type;
	}
	
	@Override
	public List<IOption> getOptions() {
		// TODO Auto-generated method stub
		return this.options;
	}
	
	@Override
	public void setOptions(List<IOption> options) {
		// TODO Auto-generated method stub
		this.options = options;
	}
	
	@Override
	public IOption getOption(Integer index) {
		// TODO Auto-generated method stub
		return this.options.get(index);
	}

}
