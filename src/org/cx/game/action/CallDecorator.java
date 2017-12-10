package org.cx.game.action;

import java.util.Map;

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
		
		//setParameterTypeValidator(new Class[]{IPlace.class, Integer.class});
	}

	@Override
	public Map<String,Integer> getConsume() {
		// TODO Auto-generated method stub
		Object proxy = ProxyFactory.getProxy(this.call);     
		return ((ICall)proxy).getConsume();
	}

	@Override
	public void setConsume(Map<String,Integer> consume) {
		// TODO Auto-generated method stub
		this.call.setConsume(consume);
	}
	
	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return this.call.getOwner();
	}

	@Override
	public Integer getRation() {
		// TODO Auto-generated method stub
		return this.call.getRation();
	}

	@Override
	public void setRation(Integer ration) {
		// TODO Auto-generated method stub
		this.call.setRation(ration);
	}

	@Override
	public void updateConsume() {
		// TODO Auto-generated method stub
		this.call.updateConsume();
	}

	@Override
	public Integer getNop() {
		// TODO Auto-generated method stub
		return call.getNop();
	}

	@Override
	public void setNop(Integer nop) {
		// TODO Auto-generated method stub
		call.setNop(nop);
	}
}
