package org.cx.game.card.skill;

import org.cx.game.action.Random;
import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.observer.NotifyInfo;

/**
 * 格挡，抵消掉敌人一次攻击
 * @author chenxian
 *
 */
public class Parry extends PassiveSkill {
	
	public Parry(Integer style, LifeCard life) {
		// TODO Auto-generated constructor stub
		super(style);
		setOwner(life);
		setAction(NotifyInfo.Card_LifeCard_Skill_Parry);
	}
	
	private Boolean invoke = true;
	
	@Override
	public void affect(Object... objects) {
		// TODO Auto-generated method stub
		super.affect(objects);
		
		invoke = false;
	}

	@Override
	public void before(Object[] args) {
		// TODO Auto-generated method stub
		if(Random.isTrigger(getOwner().getAttacked().getParryChance()))
			affect();
	}
	
	@Override
	public void finish(Object[] args) {
		// TODO Auto-generated method stub
		invoke = true;
	}

	@Override
	public Boolean isInvoke() {
		// TODO Auto-generated method stub
		return invoke;
	}
	
	@Override
	public Integer getRange() {
		// TODO Auto-generated method stub
		return 0;
	}

}
