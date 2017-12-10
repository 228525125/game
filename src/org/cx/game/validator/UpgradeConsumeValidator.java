package org.cx.game.validator;

import java.util.Map;
import java.util.Map.Entry;

import org.cx.game.action.UpgradeBuilding;
import org.cx.game.action.IUpgrade;
import org.cx.game.action.UpgradeProduct;
import org.cx.game.core.IPlayer;
import org.cx.game.tools.I18n;

public class UpgradeConsumeValidator extends Validator {

	private IUpgrade upgrade = null;
	private IPlayer player = null;
	
	public UpgradeConsumeValidator(IUpgrade upgrade, IPlayer player) {
		// TODO Auto-generated constructor stub
		this.upgrade = upgrade;
		this.player = player;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret = true;

		Map<String,Integer> res = player.getResource();
			
		for(Entry<String,Integer> entry : upgrade.getRequirement().entrySet()){
			String resType = entry.getKey();
			Integer resValue = res.get(resType);
			if(resValue<entry.getValue()){
				ret = false;
				break;
			}
		}	
		
		if(!ret)
			addMessage(I18n.getMessage(this));
		return ret;
	}
}
