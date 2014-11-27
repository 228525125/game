package org.cx.game.card.skill;

import org.cx.game.action.Random;
import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.tools.Util;

/**
 * 致命一击
 * @author chenxian
 *
 */
public class Deadly extends SimplePassiveSkill {

	private Integer chance;
	private Integer upAtkScale;
	
	public Deadly(Integer style, Integer chance, Integer upAtkScale) {
		// TODO Auto-generated constructor stub
		super(style);
		setAction(NotifyInfo.Card_LifeCard_Skill_Deadly);
		this.chance = chance;
		this.upAtkScale = upAtkScale;
	}

	@Override
	public String getIntercepterMethod() {
		// TODO Auto-generated method stub
		return "attack";
	}
	
	public Integer getChance() {
		return chance;
	}

	public void setChance(Integer chance) {
		this.chance = chance;
	}

	@Override
	public void before(Object[] args) {
		// TODO Auto-generated method stub
		if(Random.isTrigger(chance)){
			Integer upAtkValue = getOwner().getAttack().getAtk()*upAtkScale/100;
			addToEruptAtk(upAtkValue);
			affect();
		}
	}

	@Override
	public Boolean isInvoke() {
		// TODO Auto-generated method stub
		return true;
	}

}
