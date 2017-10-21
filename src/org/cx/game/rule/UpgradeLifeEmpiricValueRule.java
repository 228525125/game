package org.cx.game.rule;

import org.cx.game.action.ILifeUpgrade;
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
		ILifeUpgrade upgrade = getOwner();
		 
		
		if(upgrade.getEmpiricValue()>=upgrade.getConsume()){
			
			try {
				upgrade.getOwner().upgrade();
			} catch (RuleValidatorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public Class getInterceptable() {
		// TODO Auto-generated method stub
		return ILifeUpgrade.class;
	}
	
	@Override
	public ILifeUpgrade getOwner() {
		// TODO Auto-generated method stub
		return (ILifeUpgrade) super.getOwner();
	}

}
