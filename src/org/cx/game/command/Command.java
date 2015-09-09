package org.cx.game.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.cx.game.exception.CommandValidatorException;
import org.cx.game.exception.ValidatorException;
import org.cx.game.out.JsonOut;
import org.cx.game.validator.Errors;
import org.cx.game.validator.IValidatable;
import org.cx.game.validator.IValidator;

public class Command extends Observable implements IValidatable{
		
	protected Object parameter = null;
	private List<IValidator> validatorList = new ArrayList<IValidator>();
	private Errors errors = new Errors();
	
	public Command() {
		// TODO Auto-generated constructor stub
		super.addObserver(new JsonOut());
	}
	
	public void execute() throws ValidatorException {
		/* 
		 * 执行规则验证
		 */
		doValidator();
		
		if(hasError())
			throw new CommandValidatorException(getErrors().getMessage());
	}

	public void setParameter(Object parameter) {
		this.parameter = parameter;
	}
	
	@Override
	public void notifyObservers(Object arg) {
		// TODO Auto-generated method stub
		super.setChanged();
		super.notifyObservers(arg);
	}

	@Override
	public void addValidator(IValidator validator) {
		// TODO Auto-generated method stub
		validatorList.add(validator);
	}

	@Override
	public void deleteValidator(IValidator validator) {
		// TODO Auto-generated method stub
		validatorList.remove(validator);
	}

	@Override
	public List<IValidator> getValidators() {
		// TODO Auto-generated method stub
		return validatorList;
	}
	
	@Override
	public void doValidator() {
		// TODO Auto-generated method stub
		for(IValidator v : validatorList)
			if(!v.validate())
				errors.addError(v);
	}
	
	@Override
	public void doValidator(IValidator validator) {
		// TODO Auto-generated method stub
		if(!validator.validate())
			errors.addError(validator);
	}
	
	@Override
	public Errors getErrors() {
		// TODO Auto-generated method stub
		return errors;
	}
	
	@Override
	public Boolean hasError() {
		// TODO Auto-generated method stub
		return errors.hasError();
	}	
}
