package org.cx.game.validator;

import java.util.List;

public interface IValidatable {

	public void addValidator(IValidator validator);
	
	public void deleteValidator(IValidator validator);
	
	public List<IValidator> getValidators();
	
	public void doValidator();
	
	public Errors getErrors();
	
	public Boolean hasError();
}
