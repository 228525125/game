package org.cx.game.rule;

import java.util.Observable;

import org.cx.game.action.IAttacked;
import org.cx.game.action.IDeath;
import org.cx.game.card.LifeCard;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;

public class DeathRule implements IRule {
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		if (arg instanceof NotifyInfo) {
			NotifyInfo info = (NotifyInfo) arg;
			
			if(NotifyInfo.Card_LifeCard_State_Hp.equals(info.getType())){
				IDeath death = (IDeath) ((RuleGroup) o).getMessageSource();
				
				if(death.getHp().equals(0)){
					try {
						death.getOwner().death();
					} catch (RuleValidatorException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			if(NotifyInfo.Card_LifeCard_Action_Death.equals(info.getType())){
				IDeath death = (IDeath) ((RuleGroup) o).getMessageSource();
				
				LifeCard owner = death.getOwner();
				IPlayer player = owner.getPlayer();
				
				death.setStatus(IDeath.Status_Death);
				owner.initState();
				
				player.addToRation(-owner.getRation());
			}
		}
	}
}
