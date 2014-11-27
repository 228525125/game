package org.cx.game.action;

import org.cx.game.card.ICard;
import org.cx.game.card.skill.IActiveSkill;
import org.cx.game.exception.RuleValidatorException;

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
	}
	
	public static void test(Object...objects){
		for(Object o : objects){
			System.out.println(o);
		}
	}
	
	public static void main(String[] args) {
		test(1,new Integer[]{2,4});
	}

}
