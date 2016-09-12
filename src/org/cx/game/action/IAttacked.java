package org.cx.game.action;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;

public interface IAttacked extends IAction {
	
	public LifeCard getOwner();
	
	public Boolean getFightBack();
	
	public void setFightBack(Boolean fightBack);
}
