package org.cx.game.action;

import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.IActiveSkill;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.validator.ConjurePowerValidator;

public class ConjureDecorator extends ActionDecorator implements IConjure {

	private IConjure conjure;
	
	public ConjureDecorator(IConjure conjure) {
		super(conjure);
		// TODO Auto-generated constructor stub
		this.conjure = conjure;
		
		setParameterTypeValidator(new Class[]{IActiveSkill.class, Object[].class});
	}

	@Override
	public Integer getPower() {
		// TODO Auto-generated method stub
		return this.conjure.getPower();
	}

	@Override
	public void setPower(Integer power) {
		// TODO Auto-generated method stub
		this.conjure.setPower(power);
	}
	
	private ConjurePowerValidator conjurePowerValidator = null;
	
	@Override
	public void action(Object... objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		IActiveSkill skill = (IActiveSkill) objects[0];
		
		deleteValidator(conjurePowerValidator);
		conjurePowerValidator = new ConjurePowerValidator((LifeCard)getOwner(), skill);
		addValidator(conjurePowerValidator);
		
		super.action(objects);
	}

}
