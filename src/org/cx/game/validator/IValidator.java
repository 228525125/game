package org.cx.game.validator;

/**
 * 验证
 * @author chenxian
 *
 */
public interface IValidator {
	
	static final String ErrorMessage_NULL = "";
	
	/**
	 * 错误提示信息
	 * @return
	 */
	public String getErrorMessage();
	
	

	/**
	 * 验证
	 * @param objects
	 * @return 验证通过返回true
	 */
	public Boolean validate();
}
