package org.cx.game.npc;

import java.util.ArrayList;
import java.util.List;

import org.cx.game.card.LifeCard;
import org.cx.game.core.Player;
import org.cx.game.tools.I18n;

public class Faction extends Player {

	private String name = "";
	private LifeCard hero = null;
	
	public Faction(Integer id, String name) {
		// TODO Auto-generated constructor stub
		super(id, name);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		if(null==name)
			name = I18n.getMessage(this, getId(), "name");
		return name;
	}

	@Override
	public Integer getHomePosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setHomePosition(Integer position) {
		// TODO Auto-generated method stub
		
	}
}
