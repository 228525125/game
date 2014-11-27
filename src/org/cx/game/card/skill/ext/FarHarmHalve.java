package org.cx.game.card.skill.ext;

import org.cx.game.action.IAttack;
import org.cx.game.action.Random;
import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.SimplePassiveSkill;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;

/**
 * 远程伤害减半
 * @author chenxian
 *
 */
public class FarHarmHalve extends SimplePassiveSkill {

	private LifeCard attack;
	private Integer elevateScale;   //百分比
	
	public FarHarmHalve(Integer style, Integer chance, Integer elevateScale) {
		// TODO Auto-generated constructor stub
		super(style);
		this.elevateScale = elevateScale;
	}

	@Override
	public void before(Object[] args) {
		// TODO Auto-generated method stub
		attack = (LifeCard) ((Object[]) args[0])[0];
		if(IAttack.Mode_Far.equals(attack.getAttack().getMode())){
			Integer atk = attack.getAttack().getAtk();
			Integer elevateValue = atk*elevateScale;
			addToEruptAtk(elevateValue);
			affect();
		}
	}

	@Override
	public String getIntercepterMethod() {
		// TODO Auto-generated method stub
		return "attacked";
	}

	@Override
	public Boolean isInvoke() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Integer getRange() {
		// TODO Auto-generated method stub
		return 0;
	}
}
