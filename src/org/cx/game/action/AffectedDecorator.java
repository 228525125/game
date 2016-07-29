package org.cx.game.action;

import org.cx.game.card.magic.IMagic;

public class AffectedDecorator extends ActionDecorator implements IAffected {

	private IAffected affected = null;
	
	public AffectedDecorator(IAffected affected) {
		super(affected);
		// TODO Auto-generated constructor stub
		this.affected = affected;
		
		setParameterTypeValidator(new Class[]{IMagic.class});
	}

}
