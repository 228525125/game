package org.cx.game.validator;

/**
 * 验证器，命名规则：（修饰语）主语 + 谓语 + Validator
 * @author admin
 *
 */
public abstract class Validator implements IValidator {

	private String message = ErrorMessage_NULL;
	
	protected void addMessage(String msg){
		message += msg;
	}
	
	@Override
	public String getErrorMessage() {
		// TODO Auto-generated method stub
		return message;
	}

	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}

}
