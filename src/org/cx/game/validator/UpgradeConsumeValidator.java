package org.cx.game.validator;

import org.cx.game.action.UpgradeBuilding;
import org.cx.game.action.IUpgrade;
import org.cx.game.action.UpgradeProduct;
import org.cx.game.tools.I18n;

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
		
		if (upgrade instanceof UpgradeBuilding) {
			UpgradeBuilding bu = (UpgradeBuilding) upgrade;
			if(bu.getOwner().getPlayer().getResource()>=bu.getStandard())
				ret = true;
			else
				ret = false;
		}
		
		if (upgrade instanceof UpgradeProduct) {
			UpgradeProduct pu = (UpgradeProduct) upgrade;
			if(pu.getOwner().getOwner().getPlayer().getResource()>=pu.getStandard())
				ret = true;
			else
				ret = false;
		}
		
		if(!ret)
			addMessage(I18n.getMessage(this));
		return ret;
	}
}
