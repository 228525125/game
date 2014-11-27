package org.cx.game.validator;

import java.util.ArrayList;
import java.util.List;

/**
 * 验证后的错误集
 * @author chenxian
 *
 */
public class Errors {

	/**
	 * 存储有错误的验证器
	 */
	private List<IValidator> errors = new ArrayList<IValidator>();

	/**
	 * 获取验证错误的数量
	 * 
	 * @return 未难过的验证数
	 */
	public int getErrorCount() {
		return errors.size();
	}

	/**
	 * 检测是否验证错误结果为空，也即是否具有未通过的验证
	 * 
	 * @return 若没有出现验证错误，即所有验证都通过，则返回true，否则返回false.
	 */
	public boolean getEmpty() {
		return errors == null || errors.size() < 1;
	}

	/**
	 * 检测此次请求中是否包含验证错误，或者具有未通过的验证
	 * 
	 * @return 若有未通过的验证则返回true，否则返回false
	 */

	public boolean hasError() {
		return !getEmpty();
	}

	public void addError(IValidator validator) {
		errors.add(validator);
	}

	public String getMsg() {
		return getMessage();
	}

	/**
	 * 该方法会清空错误信息
	 * @return
	 */
	public String getMessage() {
		return toString();
	}

	public String toString() {
		String ret = "";
		for(IValidator validator: errors){
			ret += validator.getErrorMessage();
		}
		errors.clear();
		return ret;
	}

	public List<IValidator> getErrors() {
		return errors;
	}
}
