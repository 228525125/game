package org.cx.game.action;

import java.util.List;
import java.util.Map;
import java.util.Observer;

import org.cx.game.card.ICard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.ProxyFactory;
import org.cx.game.rule.IRule;
import org.cx.game.rule.RuleGroup;
import org.cx.game.rule.RuleGroupFactory;
import org.cx.game.validator.Errors;
import org.cx.game.validator.IValidator;
import org.cx.game.validator.ParameterTypeValidator;

public abstract class ActionDecorator implements IAction {

	private IAction original;
	
	public ActionDecorator(IAction action) {
		// TODO Auto-generated constructor stub
		this.original = action;
		
		action.setDecorator(this);
		
		RuleGroupFactory.bindingRule(this);
	}
	
	@Override
	public ActionDecorator getDecorator() {
		// TODO Auto-generated method stub
		return original.getDecorator();
	}
	
	@Override
	public void setDecorator(ActionDecorator decorator) {
		// TODO Auto-generated method stub
		original.setDecorator(decorator);
	}
	
	@Override
	public void setOwner(Object object) {
		// TODO Auto-generated method stub
		this.original.setOwner(object);
	}
	
	@Override
	public Object getOwner() {
		// TODO Auto-generated method stub
		return original.getOwner();
	}
	
	/*
	private ParameterTypeValidator parameterValidator = null;
	private Class[] parameterType = new Class[]{};      //用于参数的验证
	private String[] proertyName = null;
	private Object[] validatorValue = null;

	@Override
	public void setParameterTypeValidator(Class[] parameterType) {
		this.parameterType = parameterType;
	}*/
	
	/**
	 * 
	 * @param parameterType 参数类型
	 * @param proertyName 属性名称
	 * @param validatorValue 属性值，但必须为基本类型
	 
	@Override
	public void setParameterTypeValidator(Class[] parameterType, String[] proertyName, Object[] validatorValue) {
		this.parameterType = parameterType;
		this.proertyName = proertyName;
		this.validatorValue = validatorValue;
	}*/

	@Override
	public void action(Object... objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		/*
		 * 这里要考虑一下，可以把参数验证放到Command中，这里可以省略
		 
		deleteValidator(parameterValidator);
		this.parameterValidator = new ParameterTypeValidator(objects,parameterType,proertyName,validatorValue);
		addValidator(parameterValidator);
		*/
		
		/* 
		 * 执行规则验证
		 */
		original.doValidator();
		
		if(original.hasError()){
			throw new RuleValidatorException(getErrors().getMessage());
		}else{
			Object proxy = ProxyFactory.getProxy(this.original);     
			((IAction)proxy).action(objects);
		}		
	}
	
	@Override
	public void addIntercepter(IIntercepter intercepter) {
		// TODO Auto-generated method stub
		original.addIntercepter(intercepter);
	}
	
	@Override
	public synchronized void addObserver(Observer o) {
		// TODO Auto-generated method stub
		original.addObserver(o);
	}
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		original.clear();
	}
	
	@Override
	public void deleteIntercepter(IIntercepter intercepter) {
		// TODO Auto-generated method stub
		original.deleteIntercepter(intercepter);
	}
	
	@Override
	public synchronized void deleteObserver(Observer o) {
		// TODO Auto-generated method stub
		original.deleteObserver(o);
	}
	
	@Override
	public synchronized int countObservers() {
		// TODO Auto-generated method stub
		return original.countObservers();
	}
	
	@Override
	public synchronized void deleteObservers() {
		// TODO Auto-generated method stub
		original.deleteObservers();
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return original.equals(obj);
	}
	
	@Override
	public Map<String,List<IIntercepter>> getIntercepterList() {
		// TODO Auto-generated method stub
		return original.getIntercepterList();
	}
	
	@Override
	public synchronized boolean hasChanged() {
		// TODO Auto-generated method stub
		return original.hasChanged();
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return original.hashCode();
	}
	
	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		original.notifyObservers();
	}
	
	@Override
	public void notifyObservers(Object arg) {
		// TODO Auto-generated method stub
		original.notifyObservers(arg);
	}
	
	@Override
	public void addValidator(IValidator validator) {
		// TODO Auto-generated method stub
		original.addValidator(validator);
	}
	
	@Override
	public void deleteValidator(IValidator validator) {
		// TODO Auto-generated method stub
		original.deleteValidator(validator);
	}
	
	@Override
	public void doValidator() {
		// TODO Auto-generated method stub
		original.doValidator();
	}
	
	@Override
	public void doValidator(IValidator validator) {
		// TODO Auto-generated method stub
		original.doValidator(validator);
	}
	
	@Override
	public Errors getErrors() {
		// TODO Auto-generated method stub
		return original.getErrors();
	}
	
	@Override
	public List<IValidator> getValidators() {
		// TODO Auto-generated method stub
		return original.getValidators();
	}
	
	@Override
	public Boolean hasError() {
		// TODO Auto-generated method stub
		return original.hasError();
	}
}
