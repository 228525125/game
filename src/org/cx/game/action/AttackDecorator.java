package org.cx.game.action;

import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.Intercepter;
import org.cx.game.validator.AttackRangeValidator;

public class AttackDecorator extends ActionDecorator implements IAttack {
	
	private IAttack attack = null;
	
	public AttackDecorator(final IAttack attack) {
		// TODO Auto-generated constructor stub
		super(attack);
		this.attack = attack;
		
		/*
		 * 当life速度发生变化时，刷新控制列表
		 */
		this.attack.addIntercepter(new Intercepter("setSpeedChance"){

			@Override
			public void finish(Object[] args) {
				// TODO Auto-generated method stub
				attack.getOwner().getPlayer().getContext().getQueue().refurbish();    //当速度发生变化时，刷新队列顺序
			}
			
			@Override
			public Integer getLevel() {
				// TODO Auto-generated method stub
				return IIntercepter.Level_Rule;
			}
		});
	}
	
	private AttackRangeValidator attackRangeValidator = null;
	
	@Override
	public void action(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		LifeCard attacked = (LifeCard) objects[0];
		
		deleteValidator(attackRangeValidator);
		attackRangeValidator = new AttackRangeValidator((LifeCard)getOwner(),attacked);
		addValidator(attackRangeValidator);
		
		super.action(objects);
	}

	@Override
	public Integer getMode() {
		// TODO Auto-generated method stub
		return attack.getMode();
	}

	@Override
	public Integer getType() {
		// TODO Auto-generated method stub
		return attack.getType();
	}

	@Override
	public void setMode(Integer type) {
		// TODO Auto-generated method stub
		attack.setMode(type);
	}

	@Override
	public void setType(Integer atkType) {
		// TODO Auto-generated method stub
		attack.setType(atkType);
	}

	@Override
	public Integer getRange() {
		// TODO Auto-generated method stub
		return attack.getRange();
	}

	@Override
	public void setRange(Integer range) {
		// TODO Auto-generated method stub
		attack.setRange(range);
	}
	
	@Override
	public void addToRange(Integer range) {
		// TODO Auto-generated method stub
		attack.addToRange(range);
	}

	@Override
	public Integer getAccurateChance() {
		// TODO Auto-generated method stub
		return attack.getAccurateChance();
	}

	@Override
	public void setAccurateChance(Integer chance) {
		// TODO Auto-generated method stub
		attack.setAccurateChance(chance);
	}

	@Override
	public Integer getThumpChance() {
		// TODO Auto-generated method stub
		return attack.getThumpChance();
	}

	@Override
	public void setThumpChance(Integer thumpChance) {
		// TODO Auto-generated method stub
		attack.setThumpChance(thumpChance);
	}
	
	@Override
	public Integer getSpeedChance() {
		// TODO Auto-generated method stub
		return attack.getSpeedChance();
	}
	
	@Override
	public void setSpeedChance(Integer speedChance) {
		// TODO Auto-generated method stub
		attack.setSpeedChance(speedChance);
	}

	@Override
	public Integer getAtk() {
		// TODO Auto-generated method stub
		return attack.getAtk();
	}

	@Override
	public void setAtk(Integer atk) {
		// TODO Auto-generated method stub
		attack.setAtk(atk);
	}
	
	@Override
	public void addToAtk(Integer atk) {
		// TODO Auto-generated method stub
		attack.addToAtk(atk);
	}

	@Override
	public void addToSpeedChance(Integer speedChance) {
		// TODO Auto-generated method stub
		attack.addToSpeedChance(speedChance);
	}

	@Override
	public Integer getLockChance() {
		// TODO Auto-generated method stub
		return attack.getLockChance();
	}

	@Override
	public void setLockChance(Integer lockChance) {
		// TODO Auto-generated method stub
		attack.setLockChance(lockChance);
	}
}
