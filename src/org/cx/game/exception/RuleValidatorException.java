package org.cx.game.exception;

import org.cx.game.tools.I18n;

public class RuleValidatorException extends ValidatorException {

	public RuleValidatorException() {
		// TODO Auto-generated constructor stub
		super(I18n.getMessage("org.cx.game.exception.RuleValidatorException"));
	}
	
	public RuleValidatorException(String description) {
		// TODO Auto-generated constructor stub
		super(I18n.getMessage("org.cx.game.exception.RuleValidatorException")+description);
	}
}
