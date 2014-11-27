package org.cx.game.widget;

import org.cx.game.card.ICard;
import org.cx.game.intercepter.ProxyFactory;
import org.cx.game.out.JsonOut;

public class CardGroupDecorator extends ContainerDecorator implements
		ICardGroup {
	
	private ICardGroup cardGroup = null;
	
	public CardGroupDecorator(ICardGroup cardGroup) {
		super(cardGroup);
		// TODO Auto-generated constructor stub
		this.cardGroup = cardGroup;
	}
	
	@Override
	public ICard out() {
		// TODO Auto-generated method stub
		Object proxy = ProxyFactory.getProxy(cardGroup);
		ICardGroup cardGroup = ((ICardGroup)proxy);
		return cardGroup.out();
	}
}
