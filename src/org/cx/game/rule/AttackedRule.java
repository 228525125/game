package org.cx.game.rule;

import java.util.Map;
import java.util.Observable;

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

	private IAttacked attacked = null;
	
	public AttackedRule(IAttacked attacked) {
		// TODO Auto-generated constructor stub
		this.attacked = attacked;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (arg instanceof NotifyInfo) {
			NotifyInfo info = (NotifyInfo) arg;
			
			if(NotifyInfo.Card_LifeCard_Action_Attacked.equals(info.getType())){
				Map bean = (Map) info.getInfo();
				LifeCard attack = (LifeCard) bean.get("attack");
				LifeCard attacked = (LifeCard) bean.get("attacked");
				IAttack att = (IAttack) bean.get("ruleParam");
				
				//增加经验值
				Integer damage = (Integer) bean.get("damage");
				LifeUpgrade lu =(LifeUpgrade)attack.getUpgrade();
				lu.addToEmpiricValue(damage);
				
				/*
				 * 攻击有先后顺序
				 */
				IDeath death = attacked.getDeath();
				damage = attacked.getAttacked().addToArmour(damage);
				death.addToHp(damage);
			
				if(IDeath.Status_Live.equals(attacked.getDeath().getStatus())          //没有死亡 
				&& getOwner().getFightBack()                                           //是否反击过 
				&& !att.getCounterAttack()                                             //这次攻击方式是否是反击
				&& 0<getOwner().getOwner().getAttack().getAtk()                        //是否有攻击力 
				&& getOwner().getOwner().getBuff(TauntBuff.class).isEmpty()){        //没有被嘲讽
					try {
						attacked.getAttack().setCounterAttack(true);      //设置为反击
						attacked.attack(attack);
						attacked.getAttacked().setFightBack(false);
					} catch (RuleValidatorException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}
	}

	@Override
	public IAttacked getOwner() {
		// TODO Auto-generated method stub
		return this.attacked;
	}

}
