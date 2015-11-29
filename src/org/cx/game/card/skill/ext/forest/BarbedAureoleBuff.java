package org.cx.game.card.skill.ext.forest;

import org.cx.game.action.IAttack;
import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.Buff;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.Intercepter;

public class BarbedAureoleBuff extends Buff {

	private Integer returnRatio = 0;
	
	/**
	 * 
	 * @param bout
	 * @param style
	 * @param type
	 * @param func
	 * @param returnRatio 反伤比 基于100
	 * @param life
	 */
	public BarbedAureoleBuff(Integer bout, Integer style, Integer type,
			Integer func, Integer returnRatio, LifeCard life) {
		super(bout, style, type, func, life);
		// TODO Auto-generated constructor stub
		this.returnRatio = returnRatio;
	}

	@Override
	public void effect() {
		// TODO Auto-generated method stub
		IIntercepter attackedIn =  new Intercepter(){
			
			@Override
			public void after(Object[] args) {
				// TODO Auto-generated method stub
				LifeCard attack = (LifeCard) ((Object[]) args[0])[0];
				
				affect(attack);
			}
		};
		
		recordIntercepter(getOwner().getAttacked(), attackedIn);
		super.effect();
	}
	
	@Override
	public void affect(Object... objects) {
		// TODO Auto-generated method stub
		LifeCard attack = (LifeCard) objects[0];
		
		if(IAttack.Mode_Near==attack.getAttack().getMode()){        //判断近战或远程
			Integer atk = attack.getAttack().getAtk();
			Integer damage = atk*returnRatio/100;
			attack.getDeath().magicToHp(-damage);
			
			super.affect(objects);
		}
	}
}
