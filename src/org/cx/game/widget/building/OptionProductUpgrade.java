package org.cx.game.widget.building;

import java.util.ArrayList;
import java.util.List;

import org.cx.game.action.Execute;
import org.cx.game.action.ExecuteDecorator;
import org.cx.game.action.IExecute;
import org.cx.game.action.IUpgrade;
import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.ISkill;
import org.cx.game.core.Context;
import org.cx.game.core.IContext;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.tools.I18n;
import org.cx.game.validator.UpgradeConsumeValidator;
import org.cx.game.widget.IGround;
import org.cx.game.widget.building.OptionBuildingUpgrade.OptionBuildingUpgradeExecute;

public class OptionProductUpgrade extends Option implements IOption {

	private Integer type = null;
	private String name = null;
	
	public OptionProductUpgrade(Integer type) {
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
	
	private IExecute execute = null;
	
	public IExecute getExecute() {
		if(null==this.execute){
			IExecute execute = new OptionProductUpgradeExecute();
			execute.setOwner(this);
			this.execute = new ExecuteDecorator(execute);
		}
		return this.execute;
	}
	
	@Override
	public void execute(Object... objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		IBuilding building = getOwner();
		IProduct product = building.getProduct(type);
			
		getExecute().addValidator(new UpgradeConsumeValidator(product.getUpgrade(),building.getPlayer()));
		
		super.execute(objects);
			
		product.upgrade();
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
	
	public class OptionProductUpgradeExecute extends Execute {

		@Override
		public void action(Object... objects) throws RuleValidatorException {
			// TODO Auto-generated method stub
			super.action(objects);
			
			getOwner().getOwner().upgrade();
		}
	}
}
