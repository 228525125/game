package org.cx.game.action;

import org.cx.game.card.skill.IMagic;
import org.cx.game.exception.RuleValidatorException;

/**
 * 受到法术影响
 * @author chenxian
 *
 */
public class Affected extends Action implements IAffected {

	public Affected() {
		super();
		// TODO Auto-generated constructor stub
		setParameterTypeValidator(new Class[]{IMagic.class});
	}

	@Override
	public void action(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		super.action(objects);
		
		IMagic magic = (IMagic) objects[0];
		magic.affect(getOwner());
	}

}
