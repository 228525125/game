package org.cx.game.rule;

import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.cx.game.action.IAttack;
import org.cx.game.action.IAttacked;
import org.cx.game.action.IDeath;
import org.cx.game.action.LifeUpgrade;
import org.cx.game.card.LifeCard;
import org.cx.game.card.buff.AttackLockBuff;
import org.cx.game.card.buff.IBuff;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.widget.IGround;

public class AttackRule implements IRule {
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		if (arg instanceof NotifyInfo) {
			NotifyInfo info = (NotifyInfo) arg;
			
			//判断攻击距离，决定是否上锁
			if(NotifyInfo.Card_LifeCard_Action_Attack.equals(info.getType())){
				IAttack attack = (IAttack) ((RuleGroup) o).getMessageSource();
				
				Map bean = (Map) info.getInfo();
				LifeCard attacked = (LifeCard) bean.get("attacked");
				
				//判断潜行状态
				LifeCard owner = attack.getOwner();
				if(owner.getMove().getHide()){
					owner.getMove().changeHide(false);
				}
			}
		}
	}
}
