package org.cx.game.npc;

import java.util.ArrayList;
import java.util.List;

import org.cx.game.card.ICard;
import org.cx.game.core.Player;

public class Faction extends Player {

	private String name = "";
	
	public Faction(Integer id, String name) {
		// TODO Auto-generated constructor stub
		setId(id);
		this.name = name;
	}
	
	@Override
	public List<ICard> getDecks() {
		// TODO Auto-generated method stub
		return new ArrayList<ICard>();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
}
