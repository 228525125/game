package org.cx.game.card.skill;

import org.cx.game.action.Random;
import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.observer.NotifyInfo;

/**
 * 重击
 * @author chenxian
 *
 */
public class Thump extends SimplePassiveSkill {

	private Integer upAtkScale;      //攻击提升比例
	
	
	public Thump(Integer upAtkScale, LifeCard life) {
		// TODO Auto-generated constructor stub
		super();
		setOwner(life);
		this.upAtkScale = upAtkScale;
	}

	@Override
	public void before(Object[] args) {
		// TODO Auto-generated method stub
		if(Random.isTrigger(getOwner().getAttack().getThumpChance())){
			Integer atk = getOwner().getAttack().getAtk();
			Integer upAtkValue = atk*upAtkScale/100;
			addToEruptAtk(upAtkValue);
			affect();
		}
	}

	@Override
	public String getIntercepterMethod() {
		// TODO Auto-generated method stub
		return "attack";
	}

	@Override
	public Boolean isInvoke() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public Integer getLevel() {
		// TODO Auto-generated method stub
		return IIntercepter.Level_Rule;
	}

}
