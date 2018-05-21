package org.cx.game.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.ProxyFactory;
import org.cx.game.out.AbstractResponse;
import org.cx.game.out.ResponseFactory;
import org.cx.game.rule.RuleGroupFactory;
import org.cx.game.validator.Errors;
import org.cx.game.validator.IValidator;
import org.cx.game.validator.ParameterTypeValidator;

public abstract class AbstractAction extends Observable implements IAction {

	private Map<String,List<IIntercepter>> intercepterList = new HashMap<String,List<IIntercepter>>();
	private Object owner;

	public AbstractAction() {
		// TODO Auto-generated constructor stub
		addObserver(ResponseFactory.getResponse());
		
		RuleGroupFactory.bindingRule(this);
	}
	
	@Override
	public void setOwner(Object object) {
		// TODO Auto-generated method stub
		this.owner = object;
	}
	
	public Object getOwner() {
		return owner;
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
}
