package org.cx.game.action;

import org.cx.game.card.LifeCard;
import org.cx.game.rule.IRule;

public class Retaliate extends Action implements IRetaliate {

	private Boolean fightBack = false;
	
	@Override
	public IRule getRule() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean getFightBack() {
		// TODO Auto-generated method stub
		return this.fightBack;
	}

	@Override
	public void setFightBack(Boolean fightBack) {
		// TODO Auto-generated method stub
		this.fightBack = fightBack;
	}
	
	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return (LifeCard)super.getOwner();
	}

}
