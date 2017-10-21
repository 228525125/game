package org.cx.game.rule;

import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.cx.game.action.IAttack;
import org.cx.game.action.IAttacked;
import org.cx.game.action.IDeath;
import org.cx.game.action.LifeUpgrade;
import org.cx.game.card.LifeCard;
import org.cx.game.card.buff.AttackLockBuff;
import org.cx.game.card.buff.IBuff;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.widget.IGround;

public class AttackRule extends Rule implements IRule {
	
	@Override
	public String getIntercepterMethod() {
		// TODO Auto-generated method stub
		return "action";
	}
	
	@Override
	public void after(Object[] args) {
		// TODO Auto-generated method stub
		//LifeCard attacked = (LifeCard) ((Object[]) args[0])[0];
		
		IAttack attack = getOwner();
		
		//判断潜行状态
		LifeCard owner = attack.getOwner();
		if(owner.getMove().getHide()){
			owner.getMove().changeHide(false);
		}
	}
	
	@Override
	public IAttack getOwner() {
		// TODO Auto-generated method stub
		return (IAttack) super.getOwner();
	}

	@Override
	public Class getInterceptable() {
		// TODO Auto-generated method stub
		return IAttack.class;
	}
}
