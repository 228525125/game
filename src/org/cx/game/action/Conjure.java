package org.cx.game.action;

import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.IActiveSkill;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.tools.Debug;
import org.cx.game.validator.ConjurePowerValidator;

/**
 * 施法
 * @author 贤
 *
 */
public class Conjure extends Action implements IConjure {

	private Integer power = 0;
	
	public Conjure() {
		super();
		// TODO Auto-generated constructor stub
		setParameterTypeValidator(new Class[]{IActiveSkill.class, Object[].class});
	}

	public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}
	
	@Override
	public ConjureDecorator getDecorator() {
		// TODO Auto-generated method stub
		return (ConjureDecorator) super.getDecorator();
	}

	@Override
	public void action(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub		
		super.action(objects);
		
		IActiveSkill skill = (IActiveSkill) objects[0];
		addValidator(new ConjurePowerValidator((LifeCard)getOwner(), skill));
		
		doValidator();
		
		getDecorator().setPower(power-skill.getConsume());
		
		Object [] parameter = (Object[]) objects[1];
		skill.useSkill(parameter);
		
		if(!Debug.isDebug)
			getOwner().getPlayer().getContext().done();
	}

}
