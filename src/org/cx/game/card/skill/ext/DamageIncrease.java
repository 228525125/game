package org.cx.game.card.skill.ext;

import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.IBuff;
import org.cx.game.card.skill.PassiveSkill;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;

/**
 * 伤害加深 attack
 * @author chenxian
 *
 */
public class DamageIncrease extends PassiveSkill {

	private Integer bout = 0;
	private Integer scale = 0;
	public DamageIncrease(Integer style, Integer bout, Integer scale) {
		super(style);
		// TODO Auto-generated constructor stub
		this.bout = bout;
		this.scale = scale;
	}
	
	@Override
	public void affect(Object... objects) {
		// TODO Auto-generated method stub
		super.affect(objects);
		
		LifeCard attacked = (LifeCard) objects[0];
		attacked.addBuff(new DamageIncreaseBuff(bout, scale, getStyle(), IBuff.Type_Neutral, getFunc(), attacked));
	}

	@Override
	public void after(Object[] args) {
		affect(args);
	}

	@Override
	public Boolean isInvoke() {
		// TODO Auto-generated method stub
		return true;
	}

}
