package org.cx.game.card.skill;

import org.cx.game.action.Random;
import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.observer.NotifyInfo;

/**
 * 命中
 * @author 贤
 *
 */
public class Accurate extends PassiveSkill {

	private boolean invoke = false;
	
	public Accurate(Integer style, LifeCard life) {
		// TODO Auto-generated constructor stub
		super(style);
		setOwner(life);
	}
	
	@Override
	public void affect(Object... objects) {
		// TODO Auto-generated method stub
		this.invoke = false;                //没有命中
		super.affect(objects);
	}

	@Override
	public void before(Object[] args) {
		// TODO Auto-generated method stub
		if(!Random.isTrigger(getOwner().getAttack().getAccurateChance()))
			affect();
		else
			this.invoke = true;
	}

	@Override
	public Boolean isInvoke() {
		// TODO Auto-generated method stub
		return invoke;
	}

	@Override
	public Integer getLevel() {
		// TODO Auto-generated method stub
		return IIntercepter.Level_Rule;
	}
}
