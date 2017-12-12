package org.cx.game.action;

import org.cx.game.card.LifeCard;

public class PickDecorator extends ActionDecorator implements IPick {

	private IPick pick = null;
	
	public PickDecorator(IPick pick) {
		// TODO Auto-generated constructor stub
		super(pick);
		this.pick = pick;
	}
	
	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return this.pick.getOwner();
	}
}
