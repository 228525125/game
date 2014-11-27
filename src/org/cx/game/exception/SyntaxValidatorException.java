package org.cx.game.exception;

import org.cx.game.tools.I18n;

public class SyntaxValidatorException extends ValidatorException {

	public SyntaxValidatorException() {
		// TODO Auto-generated constructor stub		
		super(I18n.getMessage("org.cx.game.exception.SyntaxValidatorException"));
	}
	
	public SyntaxValidatorException(String description) {
		// TODO Auto-generated constructor stub
		super(I18n.getMessage("org.cx.game.exception.SyntaxValidatorException")+description);
	}
}
