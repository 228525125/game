package org.cx.game.action;

import org.cx.game.card.LifeCard;

/**
 * attacked的包装类，包含了一些比赛规则、代理模式等
 * @author jiuhuan
 *
 */
public class AttackedDecorator extends ActionDecorator implements IAttacked {

	private IAttacked attacked = null;
	
	public AttackedDecorator(IAttacked attacked) {
		// TODO Auto-generated constructor stub
		super(attacked);
		this.attacked = attacked;
		
		setParameterTypeValidator(new Class[]{LifeCard.class,IAttack.class});
	}

	@Override
	public Boolean getAttackBack() {
		// TODO Auto-generated method stub
		return attacked.getAttackBack();
	}

	@Override
	public void setAttackBack(Boolean attackBack) {
		// TODO Auto-generated method stub
		attacked.setAttackBack(attackBack);
	}
}
