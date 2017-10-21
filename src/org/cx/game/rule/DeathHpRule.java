package org.cx.game.rule;

import org.cx.game.action.IDeath;
import org.cx.game.exception.RuleValidatorException;

public class DeathHpRule extends Rule implements IRule {

	@Override
	public String getIntercepterMethod() {
		// TODO Auto-generated method stub
		return "addToHp";
	}
	
	@Override
	public void after(Object[] args) {
		// TODO Auto-generated method stub
		IDeath death = getOwner();
		
		if(death.getHp().equals(0)){
			try {
				death.getOwner().death();
			} catch (RuleValidatorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public Class getInterceptable() {
		// TODO Auto-generated method stub
		return IDeath.class;
	}
	
	@Override
	public IDeath getOwner() {
		// TODO Auto-generated method stub
		return (IDeath) super.getOwner();
	}

}
