package org.cx.game.card.skill.ext;

import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.ImmuneBuff;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.Intercepter;

public class IceVallumBuff extends ImmuneBuff {

	public IceVallumBuff(Integer bout, Integer style, Integer type, Integer func,
			LifeCard life) {
		super(bout, style, type, func, life);
		// TODO Auto-generated constructor stub
		setPhysical(true);
		setMagic(true);
	}
	
	@Override
	public void effect() {
		// TODO Auto-generated method stub
		super.effect();
		
		/*
		 * 进行移动，效果消失
		 */
		IIntercepter moveIn = new Intercepter(){

			@Override
			public void before(Object[] args) {
				// TODO Auto-generated method stub
				invalid();
			}
		};
		recordIntercepter(getOwner().getMove(), moveIn);
		
		/*
		 * 进行攻击，效果消失
		 */
		IIntercepter attackIn = new Intercepter(){

			@Override
			public void before(Object[] args) {
				// TODO Auto-generated method stub
				invalid();
			}
		};
		recordIntercepter(getOwner().getAttack(), attackIn);
		
		/*
		 * 进行施法，效果消失
		 */
		IIntercepter conjureIn = new Intercepter(){

			@Override
			public void before(Object[] args) {
				// TODO Auto-generated method stub
				invalid();
			}
		};
		recordIntercepter(getOwner().getConjure(), conjureIn);
	}
}
