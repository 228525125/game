package org.cx.game.action;

import org.cx.game.card.LifeCard;
import org.cx.game.card.magic.IMagic;

public class AffectedDecorator extends ActionDecorator implements IAffected {

	private IAffected affected = null;
	
	public AffectedDecorator(IAffected affected) {
		super(affected);
		// TODO Auto-generated constructor stub
		this.affected = affected;
		
		//setParameterTypeValidator(new Class[]{IMagic.class});
	}

	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return this.affected.getOwner();
	}

	@Override
	public void magicHarm(Integer harm) {
		// TODO Auto-generated method stub
		this.affected.magicHarm(harm);
	}
}
