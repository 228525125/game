package org.cx.game.rule;

import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.cx.game.action.Death;
import org.cx.game.card.LifeCard;
import org.cx.game.core.IContext;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.widget.IGround;
import org.cx.game.widget.building.BuildingCall;
import org.cx.game.widget.building.IBuilding;
import org.cx.game.widget.building.BuildingTown;

public class ContextBoutRule extends Rule implements IRule {
	
	@Override
	public String getIntercepterMethod() {
		// TODO Auto-generated method stub
		return "addBout";
	}
	
	@Override
	public void after(Object[] args) {
		// TODO Auto-generated method stub
		IPlayer player = getOwner().getControlPlayer();
		
		player.addBout();
		
		Integer tax = 0;
		IGround ground = player.getContext().getGround();
		List<IBuilding> list = ground.getBuildingList(player);
		for(IBuilding building : list){
			if(building instanceof BuildingTown){
				BuildingTown town = (BuildingTown) building;
				tax += town.getTax();
			}
		}        
		
		/*
		 * 获得控制权的玩家单位被激活
		 */
		for(LifeCard life : player.getAttendantList(Death.Status_Live)){
			Integer speed = life.getActivate().getSpeed();
			life.getActivate().addToVigour(speed);
			try {
				life.activate(true);
			} catch (RuleValidatorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public IContext getOwner() {
		// TODO Auto-generated method stub
		return (IContext) super.getOwner();
	}

	@Override
	public Class getInterceptable() {
		// TODO Auto-generated method stub
		return IContext.class;
	}
}
