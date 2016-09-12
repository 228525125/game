package org.cx.game.action;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;

public class RenewDecorator extends ActionDecorator implements IRenew {

	private IRenew original=null;
	
	public RenewDecorator(IRenew renew) {
		super(renew);
		// TODO Auto-generated constructor stub
		this.original = renew;
	}
	
	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return this.original.getOwner();
	}

}
