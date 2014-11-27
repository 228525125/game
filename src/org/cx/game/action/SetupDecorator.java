package org.cx.game.action;

import java.util.List;
import java.util.Observer;

import org.cx.game.card.TrickCard;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.ProxyFactory;

public class SetupDecorator extends ActionDecorator implements ISetup {

	private ISetup setup;
	
	public SetupDecorator(ISetup setup) {
		// TODO Auto-generated constructor stub
		super(setup);
		this.setup = setup;
	}
}
