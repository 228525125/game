package org.cx.game.card.skill;

import org.cx.game.tools.Util;

/**
 * 能力值可能发生改变的被动技能
 * @author chenxian
 *
 */
public abstract class SimplePassiveSkill extends PassiveSkill {

	private Integer eruptAtk = 0;               //临时设置，当技能执行完毕，恢复原始值
	private Integer keepAtkNewValue = 0;        //当技能执行完毕后，继续保持最新状态
	private Integer keepAtkOldValue = 0;
	private Integer eruptSpeedChance = 0;
	private Integer keepSpeedChanceNewValue = 0;
	private Integer keepSpeedChanceOldValue = 0;
	private Integer eruptImmuneDamageRatio = 0;
	private Integer keepImmuneDamageRatioNewValue = 0;
	private Integer keepImmuneDamageRatioOldValue = 0;
	private Integer eruptDodgeChance = 0;
	private Integer keepDodgeChanceNewValue = 0;
	private Integer keepDodgeChanceOldValue = 0;
	
	public SimplePassiveSkill() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void finish(Object[] args) {
		// TODO Auto-generated method stub
		if(0!=eruptAtk){
			Integer atk = getOwner().getAttack().getAtk();
			getOwner().getAttack().setAtk(atk - eruptAtk);
			addToEruptAtk(0);
		}
		
		if(0!=eruptImmuneDamageRatio){
			Integer damageChance = getOwner().getAttacked().getImmuneDamageRatio();
			getOwner().getAttacked().setImmuneDamageRatio(damageChance+eruptImmuneDamageRatio);
			addToEruptImmuneDamageRatio(0);
		}
		
		if(0!=eruptSpeedChance){
			Integer speedChance = getOwner().getAttack().getSpeedChance();
			getOwner().getAttack().setSpeedChance(speedChance - eruptSpeedChance);
			addToEruptSpeedChance(0);
		}
		
		if(0!=eruptDodgeChance){
			Integer dodgeChance = getOwner().getAttacked().getDodgeChance();
			getOwner().getAttacked().setDodgeChance(dodgeChance - eruptDodgeChance);
			addToEruptDodgeChance(0);
		}
	}
	
	@Override
	public void affect(Object... objects) {
		// TODO Auto-generated method stub
		super.affect(objects);
		
		if(0!=eruptAtk){
			Integer atk = getOwner().getAttack().getAtk();
			if(atk<eruptAtk)
				getOwner().getAttack().setAtk(0);
			else
				getOwner().getAttack().setAtk(atk + eruptAtk);
		}
		
		if(0!=keepAtkNewValue){
			getOwner().getAttack().addToAtk(keepAtkNewValue - keepAtkOldValue);
			keepAtkOldValue = keepAtkNewValue;
		}
		
		if(0!=eruptImmuneDamageRatio){
			Integer immuneDamageRatio = getOwner().getAttacked().getImmuneDamageRatio();
			getOwner().getAttacked().setImmuneDamageRatio(immuneDamageRatio + eruptImmuneDamageRatio);
		}
		
		if(0!=keepImmuneDamageRatioNewValue){
			getOwner().getAttacked().addToImmuneDamageRatio(keepImmuneDamageRatioNewValue - keepImmuneDamageRatioOldValue);
			keepImmuneDamageRatioOldValue = keepImmuneDamageRatioNewValue;
		}
		
		if(0!=eruptSpeedChance){
			Integer speedChance = getOwner().getAttack().getSpeedChance();
			getOwner().getAttack().setSpeedChance(speedChance + eruptSpeedChance);
		}
		
		if(0!=keepSpeedChanceNewValue){
			getOwner().getAttack().addToSpeedChance(keepSpeedChanceNewValue - keepSpeedChanceOldValue);
			keepSpeedChanceOldValue = keepSpeedChanceNewValue;
		}
		
		if(0!=eruptDodgeChance){
			Integer dodgeChance = getOwner().getAttacked().getDodgeChance();
			getOwner().getAttacked().setDodgeChance(dodgeChance + eruptDodgeChance);
		}
		
		if(0!=keepDodgeChanceNewValue){
			getOwner().getAttacked().addToDodgeChance(keepDodgeChanceNewValue - keepDodgeChanceOldValue);
			keepDodgeChanceOldValue = keepDodgeChanceNewValue;
		}
	}

	public void addToEruptAtk(Integer eruptAtk) {
		this.eruptAtk = eruptAtk;
	}

	public void addToKeepAtk(Integer keepAtk) {
		this.keepAtkNewValue = keepAtk;
	}

	public void addToEruptSpeedChance(Integer eruptSpeedChance) {
		this.eruptSpeedChance = eruptSpeedChance;
	}
	
	public void addToKeepSpeedChance(Integer keepSpeedChance) {
		this.keepSpeedChanceNewValue = keepSpeedChance;
	}

	public void addToEruptImmuneDamageRatio(Integer eruptImmuneDamageRatio) {
		this.eruptImmuneDamageRatio = eruptImmuneDamageRatio;
	}

	public void addToKeepImmuneDamageRatio(Integer ImmuneDamageRatio) {
		this.keepImmuneDamageRatioNewValue = ImmuneDamageRatio;
	}

	public void addToEruptDodgeChance(Integer eruptDodgeChance) {
		this.eruptDodgeChance = eruptDodgeChance;
	}

	public void addToKeepDodgeChance(Integer keepDodgeChance) {
		this.keepDodgeChanceNewValue = keepDodgeChance;
	}

}
