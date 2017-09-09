package org.cx.game.rule;

import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.cx.game.action.IActivate;
import org.cx.game.card.LifeCard;
import org.cx.game.card.buff.AttackLockBuff;
import org.cx.game.card.buff.IBuff;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;

public class ActivateRule implements IRule {
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		if (arg instanceof NotifyInfo) {
			NotifyInfo info = (NotifyInfo) arg;
			
			if(NotifyInfo.Card_LifeCard_Action_Activate.equals(info.getType())){
				IActivate activate = (IActivate) ((RuleGroup) o).getMessageSource();
				Map bean = (Map) info.getInfo();
				
				Boolean activation = (Boolean) bean.get("activate");
				LifeCard owner = activate.getOwner();
				
				if(activation){
					owner.getAttack().setAttackable(true);
					owner.getMove().setMoveable(true);
					owner.getAttacked().setFightBack(true);
					List<IBuff> buffs = activate.getOwner().getNexusBuff(AttackLockBuff.class);  //清除锁定对象
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
		}
		
	}

}
