package org.cx.game.action;

import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.IActiveSkill;

public class ConjureDecorator extends ActionDecorator implements IConjure {

	private IConjure conjure;
	
	public ConjureDecorator(IConjure conjure) {
		super(conjure);
		// TODO Auto-generated constructor stub
		this.conjure = conjure;
		
		setParameterTypeValidator(new Class[]{IActiveSkill.class, Object[].class});
	}
	
	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return this.conjure.getOwner();
	}

}
