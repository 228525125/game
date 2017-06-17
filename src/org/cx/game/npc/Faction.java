package org.cx.game.npc;

import java.util.ArrayList;
import java.util.List;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.core.Player;
import org.cx.game.tools.I18n;

public class Faction extends Player {

	private String name = "";
	private LifeCard hero = null;
	
	public Faction(Integer id) {
		// TODO Auto-generated constructor stub
		setId(id);
	}
	
	@Override
	public List<ICard> decksList() {
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

	@Override
	public LifeCard getHero() {
		// TODO Auto-generated method stub
		return this.hero;
	}

	@Override
	public void setHero(LifeCard hero) {
		// TODO Auto-generated method stub
		this.hero = hero;
	}

	@Override
	public Integer getHeroEntry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setHeroEntry(Integer position) {
		// TODO Auto-generated method stub
		
	}
}
