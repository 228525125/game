package org.cx.game.card.skill;

import org.cx.game.action.IDeath;
import org.cx.game.action.Random;
import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IIntercepter;

/**
 * 反击，受到攻击后才发生，不管是否造成伤害
 * @author chenxian
 *
 */
public class AttackBack extends PassiveSkill {

	private LifeCard attack;
	
	public AttackBack(Integer style, LifeCard life) {
		// TODO Auto-generated constructor stub
		super(style);
		setOwner(life);
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
	public void finish(Object[] args) {
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
