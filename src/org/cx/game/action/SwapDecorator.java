package org.cx.game.action;

import java.util.List;
import java.util.Observer;

import org.cx.game.card.LifeCard;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.Intercepter;
import org.cx.game.intercepter.ProxyFactory;

public class SwapDecorator extends ActionDecorator implements ISwap {

	private ISwap swap;
	
	public SwapDecorator(ISwap swap) {
		// TODO Auto-generated constructor stub
		super(swap);
		this.swap = swap;
		this.swap.addIntercepter(new Intercepter(){

			private Boolean invoke = true;

			@Override
			public void before(Object[] args) {
				// TODO Auto-generated method stub
				LifeCard swap = (LifeCard) ((Object[]) args[0])[0];
				LifeCard swaped = (LifeCard) args[1];
				if(swap.getStirps()==swaped.getStirps())   //交换的条件至少保证种族
					this.invoke = true;
				else
					this.invoke = false;
			}

			@Override
			public Boolean isInvoke() {
				// TODO Auto-generated method stub
				return invoke;
			}
			
			@Override
			public Integer getLevel() {
				// TODO Auto-generated method stub
				return IIntercepter.Level_Rule;
			}
		});
	}
	
	@Override
	public Integer getConsume(LifeCard swaped) {
		// TODO Auto-generated method stub
		return this.swap.getConsume(swaped);
	}
}
