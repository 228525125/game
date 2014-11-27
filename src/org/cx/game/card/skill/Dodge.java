package org.cx.game.card.skill;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.action.Random;
import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.observer.NotifyInfo;

/**
 * 闪避
 * @author chenxian
 *
 */
public class Dodge extends PassiveSkill {
	
	private boolean invoke = true;
	
	public Dodge(Integer style, LifeCard life) {
		// TODO Auto-generated constructor stub
		super(style);
		setOwner(life);
		setAction(NotifyInfo.Card_LifeCard_Skill_Dodge);
	}
	
	@Override
	public void affect(Object... objects) {
		// TODO Auto-generated method stub
		this.invoke = false;
		
		super.affect(objects);
	}

	@Override
	public void before(Object[] args) {
		// TODO Auto-generated method stub
		if(Random.isTrigger(getOwner().getAttacked().getDodgeChance()))
			affect();
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
	
	@Override
	public Integer getRange() {
		// TODO Auto-generated method stub
		return 0;
	}

}
