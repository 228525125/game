package org.cx.game.card.skill;

import org.cx.game.action.IDeath;
import org.cx.game.card.LifeCard;
import org.cx.game.observer.NotifyInfo;

public class AttackLock extends PassiveSkill {

	private LifeCard attacked = null;
	
	public AttackLock(Integer style, LifeCard life) {
		super(style);
		// TODO Auto-generated constructor stub
		setOwner(life);
	}

	@Override
	public void affect(Object... objects) {
		// TODO Auto-generated method stub
		super.affect(objects);
		
		new AttackLockBuff(2,getOwner(),attacked).effect();
	}
	
	@Override
	public void after(Object[] args) {
		// TODO Auto-generated method stub
		attacked = (LifeCard) ((Object[]) args[0])[0];
		
		if(IDeath.Status_Live == attacked.getDeath().getStatus())
			affect();
	}
}
