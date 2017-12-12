package org.cx.game.action;

import org.cx.game.widget.treasure.ITreasure;

public class PickedDecorator extends ActionDecorator implements IPicked {

	private IPicked picked = null;
	
	public PickedDecorator(IPicked picked) {
		// TODO Auto-generated constructor stub
		super(picked);
		
		this.picked = picked;
	}
	
	@Override
	public ITreasure getOwner() {
		// TODO Auto-generated method stub
		return this.picked.getOwner();
	}
}
