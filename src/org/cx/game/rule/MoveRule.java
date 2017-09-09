package org.cx.game.rule;

import java.util.Map;
import java.util.Observable;

import org.cx.game.action.IAttacked;
import org.cx.game.action.IMove;
import org.cx.game.card.LifeCard;
import org.cx.game.core.IPlayer;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.Intercepter;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.widget.IPlace;
import org.cx.game.widget.LandformEffect;

public class MoveRule implements IRule {

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		if (arg instanceof NotifyInfo) {
			NotifyInfo info = (NotifyInfo) arg;
			
			if(NotifyInfo.Card_LifeCard_State_Hide.equals(info.getType())){
				IMove move = (IMove) ((RuleGroup) o).getMessageSource();
				
				Map bean = (Map) info.getInfo();
				Boolean hide = (Boolean) bean.get("hide");
				LifeCard owner = move.getOwner();

			}else if(NotifyInfo.Card_LifeCard_Action_Move.equals(info.getType())){
				IMove move = (IMove) ((RuleGroup) o).getMessageSource();
				
				Map bean = (Map) info.getInfo();
				
			}
		}
	}

}
