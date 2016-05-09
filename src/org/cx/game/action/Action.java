package org.cx.game.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.cx.game.card.ICard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.out.JsonOut;
import org.cx.game.policy.IActionPolicy;
import org.cx.game.policy.IFormula;
import org.cx.game.policy.IPolicy;
import org.cx.game.validator.Errors;
import org.cx.game.validator.IValidator;
import org.cx.game.validator.ParameterTypeValidator;

public abstract class Action extends Observable implements IAction {

	private Map<String,List<IIntercepter>> intercepterList = new HashMap<String,List<IIntercepter>>();
	private ICard owner;
	private List<IValidator> validatorList = new ArrayList<IValidator>();
	private Errors errors = new Errors();
	
	private ActionDecorator decorator = null;
	
	public ActionDecorator getDecorator() {
		return decorator;
	}

	public void setDecorator(ActionDecorator decorator) {
		this.decorator = decorator;
	}

	public Action() {
		// TODO Auto-generated constructor stub
		addObserver(new JsonOut());
	}
	
	@Override
	public void setOwner(ICard card) {
		// TODO Auto-generated method stub
		this.owner = card;
	}
	
	public ICard getOwner() {
		return owner;
	}
	
	private ParameterTypeValidator parameterTypeValidator = null;
	private Class[] parameterType = new Class[]{};      //用于参数的验证
	private String proertyName = null;
	private Object[] validatorValue = null;
	
	protected void setParameterTypeValidator(Class[] parameterType) {
		this.parameterType = parameterType;
	}
	
	protected void setParameterTypeValidator(Class[] parameterType, String proertyName, Object[] validatorValue) {
		this.parameterType = parameterType;
		this.proertyName = proertyName;
		this.validatorValue = validatorValue;
	}
	
	public void action(Object...objects) throws RuleValidatorException {
		deleteValidator(parameterTypeValidator);
		this.parameterTypeValidator = new ParameterTypeValidator(objects,parameterType,proertyName,validatorValue); 
		addValidator(parameterTypeValidator);
		
		/* 
		 * 执行规则验证
		 */
		doValidator();
		
		if(hasError())
			throw new RuleValidatorException(getErrors().getMessage());
	}

	@Override
	public void addIntercepter(IIntercepter intercepter) {
		// TODO Auto-generated method stub
		List<IIntercepter> intercepters = intercepterList.get(intercepter.getIntercepterMethod());
		if(null!=intercepters){
			intercepters.add(intercepter);
		}else{
			intercepters = new ArrayList<IIntercepter>();
			intercepters.add(intercepter);
			intercepterList.put(intercepter.getIntercepterMethod(), intercepters);
		}
	}

	@Override
	public void deleteIntercepter(IIntercepter intercepter) {
		// TODO Auto-generated method stub
		/*List<IIntercepter> list = intercepterList.get(intercepter.getIntercepterMethod());
		if(null!=list){
			list.remove(intercepter);
		}*/
		
		intercepter.delete();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		intercepterList.clear();
	}

	@Override
	public Map<String,List<IIntercepter>> getIntercepterList() {
		// TODO Auto-generated method stub
		return intercepterList;
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
	
	private IActionPolicy policy = null;
	
	@Override
	public IActionPolicy getPolicy() {
		// TODO Auto-generated method stub
		return this.policy;
	}
	
	@Override
	public void setPolicy(IActionPolicy actionPolicy) {
		// TODO Auto-generated method stub
		actionPolicy.setOwner(this);
		this.policy = actionPolicy;
	}
}
