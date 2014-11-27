package org.cx.game.card.skill;

import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;

/**
 * 能力值可能发生改变的Buff
 * @author chenxian
 *
 */
public abstract class SimpleBuff extends Buff {

	private Integer keepAtkNewValue = 0;
	private Integer keepAtkOldValue = 0;
	private Integer keepSpeedChanceNewValue = 0;
	private Integer keepSpeedChanceOldValue = 0;
	private Double keepImmuneDamageRatioNewValue = 0d;
	private Double keepImmuneDamageRatioOldValue = 0d;
	private Integer keepDodgeChanceNewValue = 0;
	private Integer keepDodgeChanceOldValue = 0;
	private Integer keepEnergyNewValue = 0;
	private Integer keepEnergyOldValue = 0;
	
	public SimpleBuff(Integer bout, Integer style, Integer type,
			Integer func, LifeCard life) {
		super(bout, style, type, func, life);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void affect(Object... objects) {
		// TODO Auto-generated method stub
		super.affect(objects);
		
		if(0!=keepAtkNewValue){
			getOwner().getAttack().addToAtk(keepAtkNewValue - keepAtkOldValue);
			keepAtkOldValue = keepAtkNewValue;
		}
		
		if(0!=keepImmuneDamageRatioNewValue){
			getOwner().getAttacked().addToImmuneDamageRatio(keepImmuneDamageRatioNewValue - keepImmuneDamageRatioOldValue);
			keepImmuneDamageRatioOldValue = keepImmuneDamageRatioNewValue;
		}
		
		if(0!=keepSpeedChanceNewValue){
			getOwner().getAttack().addToSpeedChance(keepSpeedChanceNewValue - keepSpeedChanceOldValue);
			keepSpeedChanceOldValue = keepSpeedChanceNewValue;
		}
		
		if(0!=keepDodgeChanceNewValue){
			getOwner().getAttacked().addToDodgeChance(keepDodgeChanceNewValue - keepDodgeChanceOldValue);
			keepDodgeChanceOldValue = keepDodgeChanceNewValue;
		}
		
		if(0!=keepEnergyNewValue){
			getOwner().getMove().addToEnergy(keepEnergyNewValue - keepEnergyOldValue);
			keepEnergyOldValue = keepEnergyNewValue;
		}
	}
	
	@Override
	public void invalid() {
		// TODO Auto-generated method stub
		super.invalid();
		
		if(0!=keepAtkOldValue){
			getOwner().getAttack().addToAtk(-keepAtkOldValue);
			addToKeepAtk(0);
		}
		
		if(0!=keepImmuneDamageRatioOldValue){
			getOwner().getAttacked().addToImmuneDamageRatio(-keepImmuneDamageRatioOldValue);
			addToKeepImmuneDamageRatio(0d);
		}
		
		if(0!=keepSpeedChanceOldValue){
			Integer speedChance = getOwner().getAttack().getSpeedChance();
			getOwner().getAttack().setSpeedChance(speedChance - keepSpeedChanceOldValue);
			addToKeepSpeedChance(0);
		}
		
		if(0!=keepDodgeChanceOldValue){
			Integer dodgeChance = getOwner().getAttacked().getDodgeChance();
			getOwner().getAttacked().setDodgeChance(dodgeChance - keepDodgeChanceOldValue);
			addToKeepDodgeChance(0);
		}
		
		if(0!=keepEnergyOldValue){
			Integer energy = getOwner().getMove().getEnergy();
			getOwner().getMove().setEnergy(energy - keepEnergyOldValue);
			addToKeepDodgeChance(0);
		}
	}
	
	public void addToKeepAtk(Integer keepAtk) {
		this.keepAtkNewValue = keepAtk;
	}

	public void addToKeepSpeedChance(Integer keepSpeedChance) {
		this.keepSpeedChanceNewValue = keepSpeedChance;
	}

	public void addToKeepImmuneDamageRatio(Double ImmuneDamageRatio) {
		this.keepImmuneDamageRatioNewValue = ImmuneDamageRatio;
	}

	public void addToKeepDodgeChance(Integer keepDodgeChance) {
		this.keepDodgeChanceNewValue = keepDodgeChance;
	}
	
	public void addToKeepEnergy(Integer keepEnergy) {
		this.keepEnergyNewValue = keepEnergy;
	}
}
