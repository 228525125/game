package org.cx.game.ai.policy;

import java.util.ArrayList;
import java.util.List;

import org.cx.game.command.Command;
import org.cx.game.exception.ValidatorException;
import org.cx.game.validator.Errors;
import org.cx.game.validator.IValidatable;
import org.cx.game.validator.IValidator;

/**
 * 策略，是状态的逻辑，不同的状态拥有自己的策略，state.execute方法中被计算，并分解成行为；
 * 策略用到的所有信息都可应该通过T类获取；
 * @author chenxian
 *
 * @param <T> Agent 智能体
 */
public abstract class AbstractPolicy<T> implements IValidatable {
	
	public final static Integer PRI_Max = 200;
	public final static Integer PRI_Very = 180;
	public final static Integer PRI_High = 150;
	public final static Integer PRI_Middle = 100;
	public final static Integer PRI_Default = 100;
	public final static Integer PRI_Low = 50;
	public final static Integer PRI_Bottom = 20;
	public final static Integer PRI_Min = 0;
	
	private Integer pri = 0;
	private Boolean ready = false;
	
	private T agent = null;
	protected Command command = null;
	
	private List<IValidator> validatorList = new ArrayList<IValidator>();
	private Errors errors = new Errors();

	public Integer getPri() {
		// TODO Auto-generated method stub
		return this.pri;
	}
	
	public void setPri(int pri) {
		// TODO Auto-generated method stub
		this.pri = pri;
		if(0<pri)
			setReady(true);
		else
			setReady(false);
	}
	
	public Boolean isReady() {
		// TODO Auto-generated method stub
		return this.ready;
	}
	
	public void setReady(Boolean ready) {
		// TODO Auto-generated method stub
		this.ready = ready;
	}
	
	public T getAgent(){
		return this.agent;
	}
	public void setAgent(T agent){
		this.agent = agent;
	}
	
	public void calculate() {
		// TODO Auto-generated method stub
		this.errors.clearErrors();
	}
	
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
