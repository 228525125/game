package org.cx.game.action;

import org.cx.game.card.MagicCard;
import org.cx.game.intercepter.ProxyFactory;

public class ApplyDecorator extends ActionDecorator implements IApply {

	private IApply apply;
	
	public ApplyDecorator(IApply apply) {
		// TODO Auto-generated constructor stub
		super(apply);
		this.apply = apply;
	}

	@Override
	public Integer getConsume() {
		// TODO Auto-generated method stub
		return this.apply.getConsume();
	}

	@Override
	public void setConsume(Integer consume) {
		// TODO Auto-generated method stub
		this.apply.setConsume(consume);
	}

}
