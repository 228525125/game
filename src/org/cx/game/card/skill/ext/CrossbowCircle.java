package org.cx.game.card.skill.ext;

import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.CircleRangeAcitveSkill;
import org.cx.game.exception.RuleValidatorException;

/**
 * 弩.环伏
 * @author chenxian
 *
 */
public class CrossbowCircle extends CircleRangeAcitveSkill {

	private Integer range = 0;
	private Integer atkScale = 0;
	
	public CrossbowCircle(Integer consume, Integer cooldown, Integer velocity,
			Integer style, Integer func, Integer radius, Integer atkScale, Integer range) {
		super(consume, cooldown, velocity, style, func, radius);
		// TODO Auto-generated constructor stub
		this.atkScale = atkScale;
		this.range = range;
	}
	
	@Override
	public Integer getRange() {
		// TODO Auto-generated method stub
		return range;
	}
	
	@Override
	public void affect(Object... objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		super.affect(objects);
		
		LifeCard life = (LifeCard) objects[0];
		life.getDeath().magicToHp(-getOwner().getAttack().getAtk()*atkScale/100);
	}

}
