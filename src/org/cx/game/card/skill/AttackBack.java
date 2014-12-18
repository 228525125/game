package org.cx.game.card.skill;

import org.cx.game.action.IDeath;
import org.cx.game.action.Random;
import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.observer.NotifyInfo;

/**
 * 反击
 * @author chenxian
 *
 */
public class AttackBack extends PassiveSkill {

	private LifeCard attack;
	
	public AttackBack(Integer style, LifeCard life) {
		// TODO Auto-generated constructor stub
		super(style);
		setOwner(life);
		setAction(NotifyInfo.Card_LifeCard_Skill_AttackBack);
	}
	
	@Override
	public void affect(Object... objects) {
		// TODO Auto-generated method stub
		super.affect(objects);
		
		Integer attackBackChance = attack.getAttacked().getAttackBackChance();
		attack.getAttacked().setAttackBackChance(0);              //被反击后，不会再次触发反击
		
		try {
			getOwner().attack(attack);
			getOwner().getAttacked().setAttackBackChance(0);         //每回合只有一次反击机会			
		} catch (RuleValidatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			attack.getAttacked().setAttackBackChance(attackBackChance);
		}		
	}
	
	@Override
	public void after(Object[] args) {
		// TODO Auto-generated method stub		
		attack = (LifeCard) ((Object[]) args[0])[0];
		if(IDeath.Status_Live==getOwner().getDeath().getStatus() && Random.isTrigger(getOwner().getAttacked().getAttackBackChance())){			
			affect();
		}
	}
	
	@Override
	public Integer getLevel() {
		// TODO Auto-generated method stub
		return IIntercepter.Level_Rule;
	}
}
