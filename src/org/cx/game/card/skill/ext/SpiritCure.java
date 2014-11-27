package org.cx.game.card.skill.ext;

import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.ActiveSkill;
import org.cx.game.card.skill.IBuff;
import org.cx.game.card.skill.IMagic;
import org.cx.game.card.skill.IdentifyPassiveSkill;
import org.cx.game.card.skill.PassiveSkill;
import org.cx.game.exception.RuleValidatorException;

/**
 * 精神治疗
 * @author chenxian
 *
 */
public class SpiritCure extends ActiveSkill {

	private Integer bout = 0;
	private Integer scale = 0;

	public SpiritCure(Integer consume, Integer cooldown, Integer velocity,
			Integer style, Integer func, Integer bout, Integer scale) {
		super(consume, cooldown, velocity, style, func);
		// TODO Auto-generated constructor stub
		this.scale = scale;
		this.bout = bout;
		setParameterTypeValidator(new Class[]{LifeCard.class});
	}
	
	@Override
	public Integer getRange() {
		// TODO Auto-generated method stub
		return getOwner().getAttack().getRange();
	}
	
	@Override
	public void affect(Object... objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		super.affect(objects);
		
		LifeCard affected = (LifeCard) objects[0];
		new SpiritCureBuff(bout, IMagic.Style_Magic, IBuff.Type_Benefit, scale, IMagic.Func_Other, affected).effect();
	}
	
	@Override
	public void useSkill(Object... objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		super.useSkill(objects);
		
		LifeCard affected = (LifeCard) objects[0];
		affected.affected(this);
	}
	
}
