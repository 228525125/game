package org.cx.game.action;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.Intercepter;
import org.cx.game.intercepter.ProxyFactory;
import org.cx.game.validator.AttackRangeValidator;
import org.cx.game.widget.IControlQueue;
import org.cx.game.widget.IWeapon;

public class AttackDecorator extends ActionDecorator implements IAttack {
	
	private IAttack attack = null;
	
	public AttackDecorator(final IAttack attack) {
		// TODO Auto-generated constructor stub
		super(attack);
		this.attack = attack;
		
		setParameterTypeValidator(new Class[]{LifeCard.class});
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
	public void setMode(Integer type) {
		// TODO Auto-generated method stub
		attack.setMode(type);
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
	public Integer getLockChance() {
		// TODO Auto-generated method stub
		return attack.getLockChance();
	}

	@Override
	public void setLockChance(Integer lockChance) {
		// TODO Auto-generated method stub
		attack.setLockChance(lockChance);
	}
	
	@Override
	public IAttack clone() {
		// TODO Auto-generated method stub
		return attack.clone();
	}

	@Override
	public void changeMode(Integer mode) {
		// TODO Auto-generated method stub
		attack.changeMode(mode);
	}
	
	@Override
	public void addToLockChance(Integer lockChance) {
		// TODO Auto-generated method stub
		attack.addToLockChance(lockChance);
	}
	
	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return attack.getOwner();
	}

	@Override
	public Boolean getCounterAttack() {
		// TODO Auto-generated method stub
		return attack.getCounterAttack();
	}

	@Override
	public void setCounterAttack(Boolean counterAttack) {
		// TODO Auto-generated method stub
		attack.setCounterAttack(counterAttack);
	}

	@Override
	public IWeapon getWeapon() {
		// TODO Auto-generated method stub
		return attack.getWeapon();
	}

	@Override
	public void handWeapon(IWeapon weapon) {
		// TODO Auto-generated method stub
		Object proxy = ProxyFactory.getProxy(this.attack);     
		((IAttack)proxy).handWeapon(weapon);
	}

	@Override
	public Boolean getAttackable() {
		// TODO Auto-generated method stub
		return this.attack.getAttackable();
	}

	@Override
	public void setAttackable(Boolean attackable) {
		// TODO Auto-generated method stub
		this.attack.setAttackable(attackable);
	}

	@Override
	public Integer getLandformAtk() {
		// TODO Auto-generated method stub
		return this.attack.getLandformAtk();
	}

	@Override
	public void setLandformAtk(Integer landformAtk) {
		// TODO Auto-generated method stub
		this.attack.setLandformAtk(landformAtk);
	}
}
