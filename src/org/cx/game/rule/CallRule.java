package org.cx.game.rule;

import org.cx.game.action.ICall;
import org.cx.game.action.IDeath;
import org.cx.game.card.HeroCard;
import org.cx.game.card.LifeCard;
import org.cx.game.core.IPlayer;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IPlace;

public class CallRule extends Rule implements IRule {
	
	@Override
	public String getIntercepterMethod() {
		// TODO Auto-generated method stub
		return "action";
	}
	
	@Override
	public void before(Object[] args) {
		// TODO Auto-generated method stub
		ICall call = getOwner();

		LifeCard owner = call.getOwner();
		IGround ground = owner.getPlayer().getGround();
		
		/*
		 * 英雄复活要先把尸体回收
		 */
		if (owner instanceof HeroCard && null!= owner.getContainer()) {
			HeroCard hero = (HeroCard) owner;
			Integer position = hero.getContainerPosition();
			IPlace place = ground.getPlace(position);
			place.getCemetery().remove(hero);
		}
	}
	
	@Override
	public void after(Object[] args) {
		// TODO Auto-generated method stub
		ICall call = getOwner();

		LifeCard owner = call.getOwner();
		
		owner.getDeath().setStatus(IDeath.Status_Live);
		owner.getAttacked().setFightBack(true);
		
		IPlayer player = owner.getPlayer();
		player.addToResource(-owner.getCall().getConsume());
		
		player.addToRation(owner.getRation());
		
		/*
		 * 增加召唤计数器
		 */
		player.addCallCountOfPlay(1);
	}
	
	@Override
	public ICall getOwner() {
		// TODO Auto-generated method stub
		return (ICall) super.getOwner();
	}

	@Override
	public Class getInterceptable() {
		// TODO Auto-generated method stub
		return ICall.class;
	}

}
