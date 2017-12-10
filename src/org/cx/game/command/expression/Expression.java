package org.cx.game.command.expression;

import java.util.ArrayList;
import java.util.List;

import org.cx.game.exception.CommandValidatorException;
import org.cx.game.exception.SyntaxValidatorException;
import org.cx.game.validator.Errors;
import org.cx.game.validator.IValidatable;
import org.cx.game.validator.IValidator;
import org.cx.game.validator.ParameterTypeValidator;
import org.dom4j.Element;

public abstract class Expression implements IExpression, IValidatable {

	private List<IValidator> validatorList = new ArrayList<IValidator>();
	private Errors errors = new Errors();
	
	@Override
	public Object interpreter() throws SyntaxValidatorException {
		/* 
		 * 执行规则验证
		 */
		doValidator();
		
		if(hasError())
			throw new SyntaxValidatorException(getErrors().getMessage());
		
		return null;
	}
	
	/**
	 * 提取cmd中的action
	 * @param cmd
	 * @return
	 */
	public static String getAction(String cmd){
		return cmd.split(Calculator.SPACE)[0];
	}
	
	/**
	 * 提取cmd中的parameter
	 * @param cmd
	 * @return
	 */
	public static String getParameter(String cmd){
		return cmd.substring(getAction(cmd).length()+1);
		
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
		if(validator.validate())
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
