package org.cx.game.widget;

import java.util.ArrayList;
import java.util.List;

import org.cx.game.action.ActionProxyHelper;
import org.cx.game.action.Execute;
import org.cx.game.action.IAction;
import org.cx.game.core.AbstractPlayer;
import org.cx.game.corps.AbstractCorps;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.tools.I18n;
import org.cx.game.validator.Errors;
import org.cx.game.validator.IValidatable;
import org.cx.game.validator.IValidator;
import org.cx.game.validator.ParameterTypeValidator;
import org.cx.game.widget.building.AbstractProcess;
import org.cx.game.widget.building.OptionExecuteProcess;
import org.cx.game.widget.building.OptionSpacingProcess;

public abstract class AbstractOption implements IValidatable {

	private Integer number = 1;                //选项参数
	private Integer spacingWait = 0;                   //间隔等待
	private Integer executeWait = 0;                   //执行等待
	
	private String name = null;
	private Boolean allow = true;
	
	private AbstractProcess spacingProcess = null;
	private AbstractProcess executeProcess = null;
	private Object owner = null;
	
	public String getName() {
		// TODO Auto-generated method stub
		if(null==name)
			name = I18n.getMessage(this, "name");
		return name;
	}
	
	public Object getOwner() {
		// TODO Auto-generated method stub
		return this.owner;
	}

	public void setOwner(Object owner) {
		// TODO Auto-generated method stub
		this.owner = owner;
	}
	
	/**
	 * 选项参数
	 * @return 执行数量
	 */
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
	public void setSpacingWait(Integer spacingWait) {
		// TODO Auto-generated method stub
		this.spacingWait = spacingWait;
	}
	
	/**
	 * 选项被执行后，下一次可执行需间隔的回合数
	 * @param spacingWait 间隔回合数
	 */
	public Integer getSpacingWait() {
		// TODO Auto-generated method stub
		return this.spacingWait;
	}
	
	public void setExecuteWait(Integer executeWait) {
		this.executeWait = executeWait;
	}
	
	/**
	 * 选项需要等待一定回合后才被执行
	 * @param executeWait 等待回合数
	 */
	public Integer getExecuteWait() {
		// TODO Auto-generated method stub
		return this.executeWait;
	}
	
	/**
	 * 查询执行范围
	 * @param ground
	 * @return
	 */
	public abstract List<Integer> getExecuteRange(AbstractGround ground);
	
	/**
	 * 间隔剩余回合数
	 * @return
	 */
	public Integer getSpacingRemainBout() {
		// TODO Auto-generated method stub
		return null!=this.spacingProcess ? this.spacingProcess.getRemainBout() : 0;
	}
	
	/**
	 * 距离执行完成还有多少回合
	 * @return
	 */
	public Integer getExecuteRemainBout() {
		// TODO Auto-generated method stub
		return null!=this.executeProcess ? this.executeProcess.getRemainBout() : 0;
	}
	
	/**
	 * 是否可以执行，这里主要判断选项的执行周期是否结束
	 * @return
	 */
	public Boolean getAllow() {
		return allow;
	}

	public void setAllow(Boolean allow) {
		this.allow = allow;
	}
	
	/**
	 * 开始执行间隔周期
	 */
	public void cooling() {
		// TODO Auto-generated method stub		
		if(!Integer.valueOf(0).equals(this.spacingWait)){
			setAllow(false);
			this.spacingProcess = new OptionSpacingProcess(this.spacingWait, getControlQueue(), this);
		}else
			setAllow(true);
	}
	
	/**
	 * 在选项被执行前必须做的事，例如建造之前要扣减费用
	 */
	protected void beforeExecute(){
		
	}
	
	/**
	 * 在子类中，定义不同的Execute；
	 * @return
	 */
	public abstract Execute getExecute();
	
	/**
	 * 执行选项
	 * @param objects
	 */
	public void execute(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		/*
		 * 因为Execute的参数范围太大，所以只能将参数验证交给Option的子类来完成
		 */
		deleteValidator(parameterValidator);
		this.parameterValidator = new ParameterTypeValidator(objects,parameterType,proertyName,validatorValue);
		addValidator(parameterValidator);
		
		doValidator();
		
		if(hasError())
			throw new RuleValidatorException(getErrors().getMessage());
			
		firing();
		
		IAction action = new ActionProxyHelper(getExecute());
		action.action(objects);
	}
	
	private Errors errors = new Errors();	
	private List<IValidator> validatorList = new ArrayList<IValidator>();
	
	private ParameterTypeValidator parameterValidator = null;
	private Class[] parameterType = new Class[]{};      //用于参数的验证
	private String[] proertyName = null;
	private Object[] validatorValue = null;

	public void setParameterTypeValidator(Class[] parameterType) {
		this.parameterType = parameterType;
	}
	
	/**
	 * 
	 * @param parameterType 参数类型
	 * @param proertyName 属性名称
	 * @param validatorValue 属性值，但必须为基本类型
	 */
	public void setParameterTypeValidator(Class[] parameterType, String[] proertyName, Object[] validatorValue) {
		this.parameterType = parameterType;
		this.proertyName = proertyName;
		this.validatorValue = validatorValue;
	}
	
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
	
	/**
	 * 开始Execute方法进度
	 */
	private void firing() {
		if(!Integer.valueOf(0).equals(getExecuteWait())){
			setAllow(false);
			this.executeProcess = new OptionExecuteProcess(getExecuteWait(), getControlQueue(), this);
			
			beforeExecute();
		}else
			setAllow(true);
	}
	
	protected abstract AbstractControlQueue getControlQueue();

}
