package org.cx.game.exception;

import org.cx.game.tools.I18n;

public class ValidatorException extends Exception {

	public ValidatorException() {
		// TODO Auto-generated constructor stub
		super(I18n.getMessage("org.cx.game.exception.ValidatorException"));
	}
	
	public ValidatorException(String description) {
		// TODO Auto-generated constructor stub
		super(description);
	}
}
