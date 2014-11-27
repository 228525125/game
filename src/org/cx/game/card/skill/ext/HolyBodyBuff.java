package org.cx.game.card.skill.ext;

import org.cx.game.action.IDeath;
import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.Buff;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.Intercepter;

public class HolyBodyBuff extends Buff {

	public HolyBodyBuff(Integer bout, Integer style, Integer type,
			Integer func,LifeCard life) {
		super(bout, style, type, func, life);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void affect(Object... objects) {
		// TODO Auto-generated method stub
		super.affect(objects);
		
		getOwner().getDeath().magicToHp(1);
	}
	
	@Override
	public void effect() {
		// TODO Auto-generated method stub
		super.effect();
		
		IIntercepter addToHpIn = new Intercepter("addToHp") {
			
			@Override
			public void finish(Object[] args) {
				// TODO Auto-generated method stub
				if(0==getOwner().getDeath().getHp())
					affect();
			}
			
			@Override
			public Integer getOrder() {
				// TODO Auto-generated method stub
				return IIntercepter.Order_Default - 10;        //该拦截器必须在调用action之前被执行
			}
		};
		recordIntercepter(getOwner().getDeath(), addToHpIn);
	}

}
