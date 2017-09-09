package org.cx.game.rule;

import java.util.Map;
import java.util.Observable;

import org.cx.game.action.IApply;
import org.cx.game.action.IAttack;
import org.cx.game.action.IAttacked;
import org.cx.game.action.IDeath;
import org.cx.game.action.LifeUpgrade;
import org.cx.game.card.LifeCard;
import org.cx.game.card.buff.TauntBuff;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.widget.IWeapon;

public class AttackedRule implements IRule {
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		if (arg instanceof NotifyInfo) {
			NotifyInfo info = (NotifyInfo) arg;
			
			if(NotifyInfo.Card_LifeCard_Action_Attacked.equals(info.getType())){
				IAttacked attacked = (IAttacked) ((RuleGroup) o).getMessageSource();
				
				Map bean = (Map) info.getInfo();
				LifeCard attackLife = (LifeCard) bean.get("attack");
				LifeCard attackedLife = (LifeCard) bean.get("attacked");
				IAttack att = (IAttack) bean.get("ruleParam");
				
				//增加经验值
				Integer damage = (Integer) bean.get("damage");
				LifeUpgrade lu =(LifeUpgrade)attackLife.getUpgrade();
				lu.addToEmpiricValue(damage);
				
				/*
				 * 攻击有先后顺序
				 */
				IDeath death = attackedLife.getDeath();
				damage = attackedLife.getAttacked().addToArmour(damage);
				death.addToHp(damage);
			
				if(IDeath.Status_Live.equals(attackedLife.getDeath().getStatus())          //没有死亡 
				&& attacked.getFightBack()                                           //是否反击过 
				&& !att.getCounterAttack()                                             //这次攻击方式是否是反击
				&& 0<attacked.getOwner().getAttack().getAtk()                        //是否有攻击力 
				&& attacked.getOwner().getBuff(TauntBuff.class).isEmpty()){        //没有被嘲讽
					try {
						attackedLife.getAttack().setCounterAttack(true);      //设置为反击
						attackedLife.attack(attackLife);
						attackedLife.getAttacked().setFightBack(false);
					} catch (RuleValidatorException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}
	}

}
