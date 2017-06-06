package org.cx.game.action;

import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.ProxyFactory;
import org.cx.game.validator.CallConsumeValidator;
import org.cx.game.validator.CallRangeValidator;
import org.cx.game.widget.IPlace;

public class CallDecorator extends ActionDecorator implements ICall {
	
	private ICall call = null;
	
	public CallDecorator(ICall call) {
		// TODO Auto-generated constructor stub
		super(call);
		this.call = call;
		
		setParameterTypeValidator(new Class[]{IPlace.class});
		addValidator(new CallConsumeValidator((LifeCard)call.getOwner()));
	}

	@Override
	public Integer getConsume() {
		// TODO Auto-generated method stub
		Object proxy = ProxyFactory.getProxy(this.call);     
		return ((ICall)proxy).getConsume();
	}

	@Override
	public void setConsume(Integer consume) {
		// TODO Auto-generated method stub
		this.call.setConsume(consume);
	}
	
	@Override
	public void addToConsume(Integer consume) {
		// TODO Auto-generated method stub
		this.call.addToConsume(consume);
	}
	
	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return this.call.getOwner();
	}
}
