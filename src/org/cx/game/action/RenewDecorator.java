package org.cx.game.action;

public class RenewDecorator extends ActionDecorator implements IRenew {

	private IRenew original=null;
	
	public RenewDecorator(IRenew renew) {
		super(renew);
		// TODO Auto-generated constructor stub
		this.original = renew;
	}

}
