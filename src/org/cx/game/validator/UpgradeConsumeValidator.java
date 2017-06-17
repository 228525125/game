package org.cx.game.validator;

import org.cx.game.action.IUpgrade;
import org.cx.game.tools.I18n;
import org.cx.game.widget.building.BuildingUpgrade;
import org.cx.game.widget.building.ProductUpgrade;

public class UpgradeConsumeValidator extends Validator {

	private IUpgrade upgrade = null;
	
	public UpgradeConsumeValidator(IUpgrade upgrade) {
		// TODO Auto-generated constructor stub
		this.upgrade = upgrade;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret = true;
		
		if (upgrade instanceof BuildingUpgrade) {
			BuildingUpgrade bu = (BuildingUpgrade) upgrade;
			if(bu.getOwner().getPlayer().getResource()>=bu.getConsume())
				ret = true;
			else
				ret = false;
		}
		
		if (upgrade instanceof ProductUpgrade) {
			ProductUpgrade pu = (ProductUpgrade) upgrade;
			if(pu.getOwner().getOwner().getPlayer().getResource()>=pu.getConsume())
				ret = true;
			else
				ret = false;
		}
		
		if(!ret)
			addMessage(I18n.getMessage(this));
		return ret;
	}
}
