package org.cx.game.card.skill.ext;

import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.ActiveSkill;
import org.cx.game.card.skill.DizzyBuff;
import org.cx.game.exception.RuleValidatorException;

/**
 * 盾击
 * @author chenxian
 *
 */
public class ShieldHit extends ActiveSkill {

	private Integer atkScale = 0;
	private Integer bout = 0;
	
	/**
	 * 
	 * @param consume
	 * @param cooldown
	 * @param applyType
	 * @param atkScale 攻击比例
	 * @param bout 眩晕回合
	 * @param life
	 */
	public ShieldHit(Integer consume, Integer cooldown, Integer velocity, Integer style, Integer func, Integer atkScale, Integer bout) {
		super(consume, cooldown, velocity, style, func);
		// TODO Auto-generated constructor stub
		this.atkScale = atkScale;
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
		
		LifeCard life = (LifeCard) objects[0];
		Integer atk = getOwner().getAttack().getAtk();
		life.getDeath().magicToHp(-atk*atkScale/100);
		
		new DizzyBuff(bout, life).effect();
	}
	
	@Override
	public void useSkill(Object... objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		super.useSkill(objects);
		
		LifeCard life = (LifeCard) objects[0];
		life.affected(this);
		
	}
	

}
