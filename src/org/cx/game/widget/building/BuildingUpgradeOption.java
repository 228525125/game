package org.cx.game.widget.building;

import java.util.ArrayList;
import java.util.List;

import org.cx.game.exception.RuleValidatorException;
import org.cx.game.tools.I18n;
import org.cx.game.validator.UpgradeConsumeValidator;
import org.cx.game.widget.IGround;

public class BuildingUpgradeOption extends Option implements IOption {

	private String name = null;
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		if(null==name && null!=getOwner()){
			name = super.getName();
			name += I18n.getMessage(Building.class, getOwner().getType(), "name");
		}
		return name;
	}
	
	@Override
	public List<Integer> getExecuteRange(IGround ground) {
		// TODO Auto-generated method stub
		List<Integer> positionList = new ArrayList<Integer>();
		return positionList;
	}
	
	@Override
	public void setExecuteWait(Integer executeWait) {
		// TODO Auto-generated method stub
		super.setExecuteWait(executeWait);
	}
	
	@Override
	public void execute(Object... objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		super.execute(objects);
		
		IBuilding building = getOwner();
		addValidator(new UpgradeConsumeValidator(building.getUpgrade()));
		
		doValidator();
		if(hasError())
			throw new RuleValidatorException(getErrors().getMessage());
		
		building.upgrade();
	}
}
