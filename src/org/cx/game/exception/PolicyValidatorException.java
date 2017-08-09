package org.cx.game.exception;

import org.cx.game.tools.I18n;

public class PolicyValidatorException extends ValidatorException {

	public PolicyValidatorException() {
		// TODO Auto-generated constructor stub
		super(I18n.getMessage("org.cx.game.exception.CommandValidatorException"));
	}
	
	public PolicyValidatorException(String description) {
		// TODO Auto-generated constructor stub
		super(I18n.getMessage("org.cx.game.exception.CommandValidatorException")+description);
	}
}
