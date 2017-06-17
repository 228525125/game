package org.cx.game.validator;

import org.cx.game.tools.I18n;
import org.cx.game.widget.building.IBuilding;

public class SelectBuildingTypeValidator extends Validator {
	
	private Integer buildingType = 0;
	private IBuilding building = null;

	public SelectBuildingTypeValidator(IBuilding building, Integer buildingType) {
		// TODO Auto-generated constructor stub
		this.building = building;
		this.buildingType = buildingType;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		if(building.getType().equals(buildingType))
			return true;
		else{
			addMessage(I18n.getMessage(this));
			return false;
		}
	}
}
