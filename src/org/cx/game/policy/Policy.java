package org.cx.game.policy;

import java.util.ArrayList;
import java.util.List;

import org.cx.game.command.Command;
import org.cx.game.command.Invoker;
import org.cx.game.core.IPlayer;
import org.cx.game.corps.Corps;
import org.cx.game.exception.CommandValidatorException;
import org.cx.game.exception.PolicyValidatorException;
import org.cx.game.exception.ValidatorException;
import org.cx.game.validator.Errors;
import org.cx.game.validator.IValidatable;
import org.cx.game.validator.IValidator;

public abstract class Policy implements IPolicy,IValidatable {
	
	private Integer pri = 0;
	private Boolean ready = false;
	
	protected Command command = null;
	
	private IPolicyGroup groupPolicy = null;
	
	private List<IValidator> validatorList = new ArrayList<IValidator>();
	private Errors errors = new Errors();

	@Override
	public IPolicyGroup getOwner() {
		// TODO Auto-generated method stub
		return this.groupPolicy;
	}

	@Override
	public void setOwner(IPolicyGroup groupPolicy) {
		// TODO Auto-generated method stub
		this.groupPolicy = groupPolicy;
	}

	@Override
	public Integer getPri() {
		// TODO Auto-generated method stub
		return this.pri;
	}

	@Override
	public void setPri(int pri) {
		// TODO Auto-generated method stub
		this.pri = pri;
		if(0<pri)
			setReady(true);
		else
			setReady(false);
	}
	
	@Override
	public Boolean isReady() {
		// TODO Auto-generated method stub
		return this.ready;
	}
	
	@Override
	public void setReady(Boolean ready) {
		// TODO Auto-generated method stub
		this.ready = ready;
	}
	
	@Override
	public void calculate() {
		// TODO Auto-generated method stub
		this.errors.clearErrors();
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		try {
			this.command.execute();
		} catch (ValidatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
