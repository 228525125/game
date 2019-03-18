package org.cx.game.command.check;

import org.cx.game.exception.ValidatorException;
import org.cx.game.validator.IValidatable;

public interface ICheck extends IValidatable {
	
	public void check(Event event) throws ValidatorException;
	
	public Boolean isMatch(Event event) throws ValidatorException;
}
