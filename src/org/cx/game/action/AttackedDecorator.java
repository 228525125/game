package org.cx.game.action;

import org.cx.game.card.ICard;
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
		
		setParameterTypeValidator(new Class[]{LifeCard.class,IAttack.class}); //第二个参数可能是反击
	}

	@Override
	public Boolean getFightBack() {
		// TODO Auto-generated method stub
		return this.attacked.getFightBack();
	}

	@Override
	public void setFightBack(Boolean fightBack) {
		// TODO Auto-generated method stub
		this.attacked.setFightBack(fightBack);
	}
	
	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return this.attacked.getOwner();
	}
}
