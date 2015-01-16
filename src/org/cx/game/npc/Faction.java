package org.cx.game.npc;

import java.util.ArrayList;
import java.util.List;

import org.cx.game.card.ICard;
import org.cx.game.core.Player;
import org.cx.game.tools.I18n;

public class Faction extends Player {

	private String name = "";
	
	public Faction(Integer id) {
		// TODO Auto-generated constructor stub
		setId(id);
	}
	
	@Override
	public List<ICard> getDecks() {
		// TODO Auto-generated method stub
		return new ArrayList<ICard>();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		if(null==name)
			name = I18n.getMessage(this, getId(), "name");
		return name;
	}
}
