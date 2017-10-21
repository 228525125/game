package org.cx.game.rule;

import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.cx.game.action.IActivate;
import org.cx.game.card.LifeCard;
import org.cx.game.card.buff.AttackLockBuff;
import org.cx.game.card.buff.IBuff;
import org.cx.game.card.skill.IActiveSkill;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.Intercepter;
import org.cx.game.observer.NotifyInfo;

public class ActivateRule extends Rule implements IRule {
	
	@Override
	public String getIntercepterMethod() {
		// TODO Auto-generated method stub
		return "action";
	}
	
	@Override
	public void after(Object[] args) {
		// TODO Auto-generated method stub
		Boolean activation = (Boolean) ((Object[]) args[0])[0];
		IActivate activate = getOwner();
		LifeCard owner = activate.getOwner();
		
		if(activation){
			owner.getAttack().setAttackable(true);
			owner.getMove().setMoveable(true);
			owner.getAttacked().setFightBack(true);
			List<IBuff> buffs = owner.getNexusBuff(AttackLockBuff.class);  //清除锁定对象
			for(IBuff buff : buffs){
				buff.invalid();
			}
			
			activate.addToVigour(-IActivate.ActivationConsume);
		}else{
			owner.getAttack().setAttackable(false);
			owner.getMove().setMoveable(false);
			
			/*
			 * 当活力值大于一次行动消耗时，再次获得一次行动
			 */
			if(owner.getActivate().getVigour()>=IActivate.ActivationConsume){
				try {
					owner.activate(true);
				} catch (RuleValidatorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public Class getInterceptable() {
		// TODO Auto-generated method stub
		return IActivate.class;
	}
	
	@Override
	public IActivate getOwner() {
		// TODO Auto-generated method stub
		return (IActivate) super.getOwner();
	}

}
