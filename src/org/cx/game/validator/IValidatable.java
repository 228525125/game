package org.cx.game.validator;

import java.util.List;

import org.cx.game.exception.ValidatorException;

/**
 * 可以被验证的接口
 * @author chenxian
 *
 */
public interface IValidatable {

	/**
	 * 添加验证逻辑，但不执行
	 * @param validator 验证逻辑
	 */
	public void addValidator(IValidator validator);
	
	public void deleteValidator(IValidator validator);
	
	public List<IValidator> getValidators();
	
	/**
	 * 执行验证逻辑，并添加错误
	 */
	public void doValidator();
	
	/**
	 * 执行单个验证逻辑，并立即抛出异常
	 * @param validator 验证逻辑
	 * @throws ValidatorException 验证逻辑异常
	 */
	public void doValidator(IValidator validator) throws ValidatorException;
	
	public Errors getErrors();
	
	/**
	 * 是否存在错误信息
	 * @return
	 */
	public Boolean hasError();
}
