package org.cx.game.action;

import org.cx.game.card.skill.IActiveSkill;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.tools.Debug;

/**
 * 施法
 * @author 贤
 *
 */
public class Conjure extends Action implements IConjure {

	public Conjure() {
		super();
		// TODO Auto-generated constructor stub
		setParameterTypeValidator(new Class[]{IActiveSkill.class, Object[].class});
	}

	@Override
	public void action(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		IActiveSkill skill = (IActiveSkill) objects[0];
		Object [] parameter = (Object[]) objects[1];

		skill.useSkill(parameter);
		
		if(!Debug.isDebug)
			getOwner().getPlayer().getContext().done();
	}

}
