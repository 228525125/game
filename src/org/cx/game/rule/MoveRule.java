package org.cx.game.rule;

import java.util.Map;
import java.util.Observable;

import org.cx.game.action.IMove;
import org.cx.game.card.LifeCard;
import org.cx.game.core.IPlayer;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.Intercepter;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.widget.IPlace;
import org.cx.game.widget.LandformEffect;

public class MoveRule implements IRule {

	private IMove move = null;
	
	public MoveRule(IMove move) {
		// TODO Auto-generated constructor stub
		this.move = move;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (arg instanceof NotifyInfo) {
			NotifyInfo info = (NotifyInfo) arg;
			
			if(NotifyInfo.Card_LifeCard_State_Hide.equals(info.getType())){
				Map bean = (Map) info.getInfo();
				Boolean hide = (Boolean) bean.get("hide");
				LifeCard owner = getOwner().getOwner();

			}else if(NotifyInfo.Card_LifeCard_Action_Move.equals(info.getType())){
				Map bean = (Map) info.getInfo();
				Integer position = (Integer) bean.get("position");
				IPlayer player = (IPlayer) bean.get("player");
				IPlace place = player.getGround().getPlace(position);
				
				/*
				 * 生成地形优势
				 */
				LifeCard life = getOwner().getOwner();
				Integer profession = life.queryTagForCategory(LifeCard.Profession).get(0);
				life.getAttack().addToAtk(LandformEffect.getAttackAdvantage(profession, place.getLandform()));
				life.getAttacked().addToDef(LandformEffect.getDefendAdvantage(profession, place.getLandform()));
			}
		}
	}

	@Override
	public IMove getOwner() {
		// TODO Auto-generated method stub
		return this.move;
	}

}
