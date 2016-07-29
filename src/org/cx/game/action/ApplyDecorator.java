package org.cx.game.action;

import org.cx.game.card.ICard;
import org.cx.game.card.MagicCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.validator.ApplyConsumeValidator;

public class ApplyDecorator extends ActionDecorator implements IApply {

	private IApply apply;
	
	public ApplyDecorator(IApply apply) {
		// TODO Auto-generated constructor stub
		super(apply);
		this.apply = apply;
		
		setParameterTypeValidator(new Class[]{Object.class});
		addValidator(new ApplyConsumeValidator(getOwner()));
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
	
	@Override
	public MagicCard getOwner() {
		// TODO Auto-generated method stub
		return (MagicCard) this.apply.getOwner();
	}

}
