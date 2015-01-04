package org.cx.game.card.skill;

import org.cx.game.action.Random;
import org.cx.game.card.LifeCard;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.Intercepter;
import org.cx.game.tools.Debug;

public class AttackLockBuff extends Buff {

	private LifeCard attack = null;
	
	public AttackLockBuff(Integer bout, LifeCard attack, LifeCard life) {
		super(bout, IMagic.Style_physical, IBuff.Type_Harm, IMagic.Func_Astrict, life);
		// TODO Auto-generated constructor stub
		attack = this.attack;
	}
	
	@Override
	public void affect(Object... objects) {
		// TODO Auto-generated method stub
		super.affect(objects);
		
		if(!Debug.isDebug)
			getOwner().getPlayer().getContext().done();
	}
	
	@Override
	public void effect() {
		// TODO Auto-generated method stub
		super.effect();
		
		IIntercepter attackIn = new Intercepter(){
			
			private boolean invoke = true;
			
			@Override
			public void before(Object[] args) {
				// TODO Auto-generated method stub				
				LifeCard attacked = (LifeCard) ((Object[]) args[0])[0];
				
				Integer chance = (100-attack.getAttack().getLockChance())*getOwner().getMove().getFleeChance()/100;
				if(!attacked.equals(attack) && !Random.isTrigger(chance)){
					invoke = false;
					affect();
				}
			}
			
			@Override
			public Boolean isInvoke() {
				// TODO Auto-generated method stub
				return invoke;
			}
		};
		recordIntercepter(getOwner().getAttack(), attackIn);
		
		IIntercepter moveIn = new Intercepter() {
			
			private boolean invoke = true;
			
			@Override
			public void before(Object[] args) {
				// TODO Auto-generated method stub
				if(!Random.isTrigger(getOwner().getMove().getFleeChance())){
					invoke = false;
					affect();
				}
			}
			
			@Override
			public Boolean isInvoke() {
				// TODO Auto-generated method stub
				return invoke;
			}
		};
		recordIntercepter(getOwner().getMove(), moveIn);
	}

}
