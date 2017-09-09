package org.cx.game.rule;

import java.util.Observable;

import org.cx.game.action.IActivate;
import org.cx.game.action.IApply;
import org.cx.game.core.IPlayer;
import org.cx.game.observer.NotifyInfo;

public class ApplyRule implements IRule {
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		if (arg instanceof NotifyInfo) {
			NotifyInfo info = (NotifyInfo) arg;
			
			if(NotifyInfo.Card_MagicCard_Apply.equals(info.getType())){
				IApply apply = (IApply) ((RuleGroup) o).getMessageSource();
				IPlayer player = apply.getOwner().getPlayer();
				player.addToResource(-apply.getConsume());
			}
		}
	}

}
