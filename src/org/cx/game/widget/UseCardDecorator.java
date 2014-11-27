package org.cx.game.widget;

import java.util.ArrayList;
import java.util.Iterator;

import org.cx.game.card.ICard;
import org.cx.game.core.IPlayer;
import org.cx.game.intercepter.ProxyFactory;
import org.cx.game.out.JsonOut;

public class UseCardDecorator extends ContainerDecorator implements IUseCard {
	
	private IUseCard useCard = null;
	
	public UseCardDecorator(IUseCard useCard) {
		// TODO Auto-generated constructor stub
		super(useCard);
		
		this.useCard = useCard;
	}
}
