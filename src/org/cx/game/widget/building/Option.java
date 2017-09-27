package org.cx.game.widget.building;

import java.util.ArrayList;
import java.util.List;

import org.cx.game.action.Chuck;
import org.cx.game.action.ChuckDecorator;
import org.cx.game.action.Execute;
import org.cx.game.action.ExecuteDecorator;
import org.cx.game.action.IChuck;
import org.cx.game.action.IExecute;
import org.cx.game.exception.OptionValidatorException;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.exception.ValidatorException;
import org.cx.game.tools.I18n;
import org.cx.game.validator.Errors;
import org.cx.game.validator.IValidator;
import org.cx.game.validator.ParameterTypeValidator;
import org.cx.game.widget.IGround;

public abstract class Option implements IOption {

	private String name = null;
	private List<IValidator> validatorList = new ArrayList<IValidator>();
	private Errors errors = new Errors();
	
	private IBuilding owner = null;
	
	private Integer spacingWait = 0;                   //间隔等待
	private Integer executeWait = 0;                   //执行等待
	private Process spacingProcess = null;
	private Process executeProcess = null;
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		if(null==name)
			name = I18n.getMessage(this, "name");
		return name;
	}
	
	@Override
	public IBuilding getOwner() {
		// TODO Auto-generated method stub
		return this.owner;
	}
	
	@Override
	public void setOwner(IBuilding building) {
		// TODO Auto-generated method stub
		this.owner = building;
	}
	
	@Override
	public void setSpacingWait(Integer spacingWait) {
		// TODO Auto-generated method stub
		this.spacingWait = spacingWait;
	}
	
	public void setExecuteWait(Integer executeWait) {
		this.executeWait = executeWait;
	}
	
	@Override
	public Integer getSpacingRemainBout() {
		// TODO Auto-generated method stub
		return null!=this.spacingProcess ? this.spacingProcess.getRemainBout() : 0;
	}
	
	@Override
	public Integer getExecuteRemainBout() {
		// TODO Auto-generated method stub
		return null!=this.executeProcess ? this.executeProcess.getRemainBout() : 0;
	}
	
	@Override
	public void cooling() {
		// TODO Auto-generated method stub		
		if(!Integer.valueOf(0).equals(this.spacingWait)){
			setAllow(false);
			this.spacingProcess = new OptionSpacingProcess(this.spacingWait, this);
		}else
			setAllow(true);
	}
	
	/**
	 * 开始Execute方法进度
	 */
	private void firing() {
		if(!Integer.valueOf(0).equals(this.executeWait)){
			setAllow(false);
			this.executeProcess = new OptionExecuteProcess(executeWait, this);
		}else
			setAllow(true);
	}
	
	private Boolean allow = true;

	public Boolean getAllow() {
		return allow;
	}

	public void setAllow(Boolean allow) {
		this.allow = allow;
	}

	private ParameterTypeValidator parameterValidator = null;
	private Class[] parameterType = new Class[]{};      //用于参数的验证
	private String[] proertyName = null;
	private Object[] validatorValue = null;

	protected void setParameterTypeValidator(Class[] parameterType) {
		this.parameterType = parameterType;
	}
	
	/**
	 * 
	 * @param parameterType 参数类型
	 * @param proertyName 属性名称
	 * @param validatorValue 属性值，但必须为基本类型
	 */
	protected void setParameterTypeValidator(Class[] parameterType, String[] proertyName, Object[] validatorValue) {
		this.parameterType = parameterType;
		this.proertyName = proertyName;
		this.validatorValue = validatorValue;
	}
	
	/**
	 * 执行选项
	 */
	private IExecute execute = null;

	public IExecute getExecute() {
		if(null==this.execute){
			IExecute execute = new Execute();
			execute.setOwner(this);
			this.execute = new ExecuteDecorator(execute);
		}
		return this.execute;
	}
	
	@Override
	public void execute(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		deleteValidator(parameterValidator);
		this.parameterValidator = new ParameterTypeValidator(objects,parameterType,proertyName,validatorValue); 
		addValidator(parameterValidator);
		
		/* 
		 * 执行规则验证
		 */
		doValidator();
		
		if(hasError() && getAllow())
			throw new RuleValidatorException(getErrors().getMessage());
		
		firing();
		
		this.execute.action(objects);
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
