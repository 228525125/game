package org.cx.game.action;

import org.cx.game.card.ICard;
import org.cx.game.intercepter.ProxyFactory;

public class ChuckDecorator extends ActionDecorator implements IChuck {

	private IChuck chuck;
	
	public ChuckDecorator(IChuck chuck) {
		// TODO Auto-generated constructor stub
		super(chuck);
		this.chuck = chuck;
	}
}
