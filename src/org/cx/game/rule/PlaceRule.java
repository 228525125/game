package org.cx.game.rule;

import java.util.Map;
import java.util.Observable;

import org.cx.game.card.LifeCard;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IPlace;
import org.cx.game.widget.LandformEffect;

public class PlaceRule implements IRule {

	private IPlace place = null;
	
	public PlaceRule(IPlace place) {
		// TODO Auto-generated constructor stub
		this.place = place;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (arg instanceof NotifyInfo) {
			NotifyInfo info = (NotifyInfo) arg;
			
			if(NotifyInfo.Container_Place_In.equals(info.getType())){
				/*
				 * 生成地形优势
				 */
				Map bean = (Map) info.getInfo();
				LifeCard life = (LifeCard) bean.get("card");
				Integer profession = life.queryTagForCategory(LifeCard.Profession).get(0);
				life.getAttack().addToAtk(LandformEffect.getAttackAdvantage(profession, place.getLandform()));
				life.getAttacked().addToDef(LandformEffect.getDefendAdvantage(profession, place.getLandform()));
			}
		}
	}

	@Override
	public Object getOwner() {
		// TODO Auto-generated method stub
		return null;
	}

}
