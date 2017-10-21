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

public class MoveRule extends Rule implements IRule {
	
	@Override
	public String getIntercepterMethod() {
		// TODO Auto-generated method stub
		return "action";
	}
	
	@Override
	public void after(Object[] args) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Class getInterceptable() {
		// TODO Auto-generated method stub
		return IMove.class;
	}
	
	@Override
	public IMove getOwner() {
		// TODO Auto-generated method stub
		return (IMove) super.getOwner();
	}

}
