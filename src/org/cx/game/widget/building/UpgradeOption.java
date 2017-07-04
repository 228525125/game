package org.cx.game.widget.building;

import java.util.ArrayList;
import java.util.List;

import org.cx.game.action.IUpgrade;
import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.ISkill;
import org.cx.game.core.Context;
import org.cx.game.core.IContext;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.tools.I18n;
import org.cx.game.validator.UpgradeConsumeValidator;
import org.cx.game.widget.IGround;

public class UpgradeOption extends Option implements IOption {

	private Integer type = null;
	private String name = null;
	
	public UpgradeOption() {
		// TODO Auto-generated constructor stub
	}
	
	public UpgradeOption(Integer type) {
		// TODO Auto-generated constructor stub
		this.type = type;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		if(null==name){
			name = super.getName();
			name += I18n.getMessage(Product.class, type, "name");
		}
		return name;
	}
	
	@Override
	public void execute(Object... objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		super.execute(objects);
		
		if(null==this.type){
			IBuilding building = getOwner();
			addValidator(new UpgradeConsumeValidator(building.getUpgrade()));
			
			doValidator();
			if(hasError())
				throw new RuleValidatorException(getErrors().getMessage());
			
			building.upgrade();
			
		}else{
			IBuilding building = getOwner();
			IProduct product = building.getProduct(type);
			
			addValidator(new UpgradeConsumeValidator(product.getUpgrade()));
			
			doValidator();
			if(hasError())
				throw new RuleValidatorException(getErrors().getMessage());
			
			product.upgrade();
		}
	}

	@Override
	public List<Integer> getExecuteRange(IGround ground) {
		// TODO Auto-generated method stub
		List<Integer> positionList = new ArrayList<Integer>();
		return positionList;
	}
}
