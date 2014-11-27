package org.cx.game.action;

import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.validator.CallConsumeValidator;
import org.cx.game.validator.CallRangeValidator;
import org.cx.game.widget.IPlace;

public class CallDecorator extends ActionDecorator implements ICall {
	
	private ICall call = null;
	
	public CallDecorator(ICall call) {
		// TODO Auto-generated constructor stub
		super(call);
		this.call = call;
		
		addValidator(new CallConsumeValidator((LifeCard)call.getOwner()));
	}
	
	private CallRangeValidator callRangeValidator = null;
	
	@Override
	public void action(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		IPlace place = (IPlace) objects[0];
		
		deleteValidator(callRangeValidator);
		callRangeValidator = new CallRangeValidator(getOwner().getPlayer(),place);
		addValidator(callRangeValidator);
		
		super.action(objects);
	}

	@Override
	public Integer getConsume() {
		// TODO Auto-generated method stub
		return this.call.getConsume();
	}

	@Override
	public void setConsume(Integer consume) {
		// TODO Auto-generated method stub
		this.call.setConsume(consume);
	}
}
