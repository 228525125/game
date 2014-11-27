package org.cx.game.exception;

import org.cx.game.tools.I18n;

public class CommandValidatorException extends ValidatorException {

	public CommandValidatorException() {
		// TODO Auto-generated constructor stub
		super(I18n.getMessage("org.cx.game.exception.CommandValidatorException"));
	}
	
	public CommandValidatorException(String description) {
		// TODO Auto-generated constructor stub
		super(I18n.getMessage("org.cx.game.exception.CommandValidatorException")+description);
	}
}

