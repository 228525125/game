package org.cx.game.validator;

import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.IActiveSkill;
import org.cx.game.tools.I18n;

/**
 * 施法所需能量是否足够
 * @author chenxian
 *
 */
public class ConjurePowerValidator extends Validator {

	private LifeCard life = null;
	private IActiveSkill skill = null;
	
	public ConjurePowerValidator(LifeCard life ,IActiveSkill skill) {
		// TODO Auto-generated constructor stub
		this.life = life;
		this.skill = skill;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Integer consume = skill.getConsume();
		if(life.getConjure().getPower()>=consume)
			return true;
		else{
			addMessage(I18n.getMessage(this));
			return false;
		}
	}
}
