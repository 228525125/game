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
	
	@Override
	public Integer getArmour() {
		// TODO Auto-generated method stub
		return attacked.getArmour();
	}

	@Override
	public void setArmour(Integer armour) {
		// TODO Auto-generated method stub
		attacked.setArmour(armour);
	}

	@Override
	public Integer addToArmour(Integer armour) {
		// TODO Auto-generated method stub
		return attacked.addToArmour(armour);
	}

	@Override
	public Integer getDef() {
		// TODO Auto-generated method stub
		return attacked.getDef();
	}

	@Override
	public void setDef(Integer def) {
		// TODO Auto-generated method stub
		attacked.setDef(def);
	}

	@Override
	public Integer getLandformDef() {
		// TODO Auto-generated method stub
		return this.attacked.getLandformDef();
	}

	@Override
	public void setLandformDef(Integer landformDef) {
		// TODO Auto-generated method stub
		this.attacked.setLandformDef(landformDef);
	}

	@Override
	public Integer getExtraDef() {
		// TODO Auto-generated method stub
		return this.attacked.getExtraDef();
	}

	@Override
	public void setExtraDef(Integer extraDef) {
		// TODO Auto-generated method stub
		this.attacked.setExtraDef(extraDef);
	}

	@Override
	public void updateDef() {
		// TODO Auto-generated method stub
		this.attacked.updateDef();
	}

	@Override
	public void addToDef(Integer def) {
		// TODO Auto-generated method stub
		this.attacked.addToDef(def);
	}
}
