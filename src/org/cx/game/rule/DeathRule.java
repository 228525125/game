package org.cx.game.rule;

import org.cx.game.action.Death;
import org.cx.game.action.IDeath;
import org.cx.game.card.LifeCard;

public class DeathRule extends Rule implements IRule {
	
	@Override
	public String getIntercepterMethod() {
		// TODO Auto-generated method stub
		return "action";
	}
	
	@Override
	public void after(Object[] args) {
		// TODO Auto-generated method stub
		IDeath death = getOwner();
		
		LifeCard owner = death.getOwner();
		
		if(owner.getHero()){
			death.setStatus(IDeath.Status_Exist);
			owner.getAttack().handWeapon(null);
		}else{
			death.setStatus(IDeath.Status_Death);
		}
		
		owner.initState();
	}

	@Override
	public Class getInterceptable() {
		// TODO Auto-generated method stub
		return Death.class;
	}
	
	@Override
	public Death getOwner() {
		// TODO Auto-generated method stub
		return (Death) super.getOwner();
	}
	
	
}
