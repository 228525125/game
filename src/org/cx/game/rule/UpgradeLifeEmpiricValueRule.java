package org.cx.game.rule;

import org.cx.game.action.IUpgradeLife;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;

public class UpgradeLifeEmpiricValueRule extends Rule implements IRule {

	@Override
	public String getIntercepterMethod() {
		// TODO Auto-generated method stub
		return "addToEmpiricValue";
	}
	

	@Override
	public void after(Object[] args) {
		// TODO Auto-generated method stub
		Integer empiricValue = (Integer) args[0];
		
		if(0<empiricValue){
			IUpgradeLife upgrade = getOwner();
			Integer req = upgrade.getRequirement().get(IPlayer.EmpiricValue);
			if(upgrade.getEmpiricValue()>=req){
				
				try {
					upgrade.getOwner().upgrade();
				} catch (RuleValidatorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public Class getInterceptable() {
		// TODO Auto-generated method stub
		return IUpgradeLife.class;
	}
	
	@Override
	public IUpgradeLife getOwner() {
		// TODO Auto-generated method stub
		return (IUpgradeLife) super.getOwner();
	}

}
