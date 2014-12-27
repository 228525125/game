package org.cx.game.card.skill;

import java.util.HashMap;
import java.util.Map;

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
	
	private LifeCard attack;
	
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
		
		/*
		 * 格挡后仍然会反击
		 */
		if(Random.isTrigger(getOwner().getAttacked().getAttackBackChance())){   
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", getOwner().getPlayer());
			map.put("container", getOwner().getContainer());
			map.put("card", getOwner());
			map.put("position", getOwner().getContainerPosition());
			NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Skill_AttackBack,map);
			notifyObservers(info);
			
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
	}

	@Override
	public void before(Object[] args) {
		// TODO Auto-generated method stub
		attack = (LifeCard) ((Object[]) args[0])[0];
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
