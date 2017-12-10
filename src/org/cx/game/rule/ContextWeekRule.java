package org.cx.game.rule;

import java.util.List;

import org.cx.game.core.IContext;
import org.cx.game.widget.IGround;
import org.cx.game.widget.building.BuildingCall;
import org.cx.game.widget.building.IBuilding;
import org.cx.game.widget.building.BuildingTown;

public class ContextWeekRule extends Rule implements IRule {

	@Override
	public String getIntercepterMethod() {
		// TODO Auto-generated method stub
		return "addWeek";
	}
	
	@Override
	public void after(Object[] args) {
		// TODO Auto-generated method stub
		
		/*
		 * 产出
		 */
		IGround ground = getOwner().getControlPlayer().getGround();
		List<IBuilding> list = ground.getBuildingList();
		for(IBuilding building : list){
			if(building instanceof BuildingTown){
				BuildingTown town = (BuildingTown) building;
				for(IBuilding innerBuilding :town.getBuildings()){
					if(innerBuilding instanceof BuildingCall){
						BuildingCall bc = (BuildingCall) innerBuilding;
						bc.output();           
					}
				}
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
