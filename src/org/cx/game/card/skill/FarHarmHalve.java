package org.cx.game.card.skill;

import org.cx.game.action.IAttack;
import org.cx.game.action.Random;
import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.SimplePassiveSkill;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.widget.IGround;

/**
 * 远程伤害减半
 * @author chenxian
 *
 */
public class FarHarmHalve extends SimplePassiveSkill {

	private LifeCard attacked;
	private Integer atkScale = -50;   //百分比
	
	public FarHarmHalve(Integer atkScale, LifeCard life) {
		// TODO Auto-generated constructor stub
		super();
		this.atkScale = atkScale;
		setOwner(life);
	}

	@Override
	public void before(Object[] args) {
		// TODO Auto-generated method stub
		attacked = (LifeCard) ((Object[]) args[0])[0];
		
		IGround ground = getOwner().getPlayer().getGround();
		Integer distance = ground.easyDistance(attacked.getContainerPosition(), getOwner().getContainerPosition());
		
		if(IAttack.Mode_Far.equals(getOwner().getAttack().getMode()) && 2>distance){
			Integer atk = getOwner().getAttack().getAtk();
			Integer atkValue = atk*atkScale/100;
			addToEruptAtk(atkValue);
			
			getOwner().getAttack().setMode(IAttack.Mode_Near);
			affect();
		}
	}
	
	@Override
	public void finish(Object[] args) {
		// TODO Auto-generated method stub
		getOwner().getAttack().setMode(IAttack.Mode_Far);
	}
	
	@Override
	public Integer getLevel() {
		// TODO Auto-generated method stub
		return IIntercepter.Level_Rule;
	}
}
