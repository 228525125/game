package org.cx.game.rule;

import java.util.Map;
import java.util.Observable;

import org.cx.game.action.IAttacked;
import org.cx.game.card.LifeCard;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IPlace;
import org.cx.game.widget.LandformEffect;

public class PlaceRule extends Rule implements IRule {
	
	@Override
	public String getIntercepterMethod() {
		// TODO Auto-generated method stub
		return "in";
	}
	
	public void after(Object[] args) {
		IPlace place = getOwner();
		
		/*
		 * 生成地形优势
		 */
		LifeCard life = (LifeCard) args[0];
		Integer profession = life.queryTagForCategory(LifeCard.Profession).get(0);
		life.getAttack().setLandformAtk(life.getAtk()*LandformEffect.getAttackAdvantage(profession, place.getLandform())/100);
		life.getAttacked().setLandformDef(life.getDef()*LandformEffect.getDefendAdvantage(profession, place.getLandform())/100);
	};

	@Override
	public Class getInterceptable() {
		// TODO Auto-generated method stub
		return IPlace.class;
	}
	
	@Override
	public IPlace getOwner() {
		// TODO Auto-generated method stub
		return (IPlace) super.getOwner();
	}
	

}
