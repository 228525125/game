package org.cx.game.card.skill.ext;

import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.ActiveSkill;
import org.cx.game.exception.RuleValidatorException;

/**
 * 传送.定
 * @author chenxian
 *
 */
public class TransmitBack extends ActiveSkill {

	private Integer bout;
	
	public TransmitBack(Integer consume, Integer cooldown, Integer velocity,
			Integer style, Integer func, Integer bout) {
		super(consume, cooldown, velocity, style, func);
		// TODO Auto-generated constructor stub
		this.bout = bout;
		setParameterTypeValidator(new Class[]{LifeCard.class});
	}

	@Override
	public Integer getRange() {
		// TODO Auto-generated method stub
		return getOwner().getAttack().getRange();
	}
	
	@Override
	public void useSkill(Object... objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		super.useSkill(objects);
		
		LifeCard life = (LifeCard) objects[0];
	}

}
