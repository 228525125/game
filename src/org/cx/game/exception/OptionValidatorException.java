package org.cx.game.exception;

import org.cx.game.tools.I18n;

public class OptionValidatorException extends ValidatorException {

	public OptionValidatorException() {
		// TODO Auto-generated constructor stub
		super(I18n.getMessage("org.cx.game.exception.OptionValidatorException"));
	}
	
	public OptionValidatorException(String description) {
		// TODO Auto-generated constructor stub
		super(I18n.getMessage("org.cx.game.exception.OptionValidatorException")+description);
	}
}
