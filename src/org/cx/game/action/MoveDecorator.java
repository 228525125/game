package org.cx.game.action;

import org.cx.game.card.LifeCard;
import org.cx.game.card.buff.IBuff;
import org.cx.game.card.buff.TiredAttackBuff;
import org.cx.game.card.magic.IMagic;
import org.cx.game.card.skill.ISkill;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.Intercepter;
import org.cx.game.intercepter.ProxyFactory;
import org.cx.game.validator.MoveRangeValidator;
import org.cx.game.widget.IPlace;

public class MoveDecorator extends ActionDecorator implements IMove {

	private IMove move = null;
	
	public MoveDecorator(IMove move) {
		// TODO Auto-generated constructor stub
		super(move);
		this.move = move;
		
		setParameterTypeValidator(new Class[]{IPlace.class});
		
		if (move instanceof FarMove) {        //远程单位，添加"疲劳攻击"设置
			final FarMove fm = (FarMove) move;
			final LifeCard life = (LifeCard) move.getOwner();
			
			this.move.addIntercepter(new Intercepter(){

				@Override
				public void finish(Object[] args) {
					new TiredAttackBuff(1,fm.getTiredAttackScale(),life).effect();
				}
				
				@Override
				public Integer getLevel() {
					// TODO Auto-generated method stub
					return IIntercepter.Level_Rule;
				}
			});
		}
	}
	
	@Override
	public Integer getType() {
		// TODO Auto-generated method stub
		return this.move.getType();
	}
	
	@Override
	public Integer getConsume() {
		// TODO Auto-generated method stub
		return this.move.getConsume();
	}

	@Override
	public Integer getEnergy() {
		// TODO Auto-generated method stub
		return move.getEnergy();
	}

	@Override
	public void setEnergy(Integer energy) {
		// TODO Auto-generated method stub
		move.setEnergy(energy);
	}

	@Override
	public void setType(Integer type) {
		// TODO Auto-generated method stub
		move.setType(type);
	}

	@Override
	public Boolean getMoveable() {
		// TODO Auto-generated method stub
		return move.getMoveable();
	}

	@Override
	public void setMoveable(Boolean moveable) {
		// TODO Auto-generated method stub
		move.setMoveable(moveable);
	}

	@Override
	public void addToEnergy(Integer energy) {
		// TODO Auto-generated method stub
		move.addToEnergy(energy);
	}
	
	private MoveRangeValidator moveRangeValidator = null;
	
	@Override
	public void action(Object... objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		IPlace place = (IPlace) objects[0];
		
		deleteValidator(moveRangeValidator);
		moveRangeValidator = new MoveRangeValidator((LifeCard) getOwner(), place);
		addValidator(moveRangeValidator);
		
		super.action(objects);
		
		setMoveable(false);     //一个回合只能移动一次
	}

	@Override
	public Integer getFlee() {
		// TODO Auto-generated method stub
		return move.getFlee();
	}

	@Override
	public void setFlee(Integer fleeChance) {
		// TODO Auto-generated method stub
		move.setFlee(fleeChance);
	}

	@Override
	public Boolean getHide() {
		// TODO Auto-generated method stub
		return move.getHide();
	}

	@Override
	public void setHide(Boolean hide) {
		// TODO Auto-generated method stub
		move.setHide(hide);
	}

	@Override
	public void changeHide(Boolean hide) {
		// TODO Auto-generated method stub
		move.changeHide(hide);
	}

	@Override
	public void changeType(Integer type) {
		// TODO Auto-generated method stub
		move.changeType(type);
	}

	@Override
	public void addToFlee(Integer flee) {
		// TODO Auto-generated method stub
		move.addToFlee(flee);
	}

}
