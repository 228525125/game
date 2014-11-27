package org.cx.game.command;

import org.cx.game.exception.ValidatorException;

public interface IExternalCommand {

	public void execute(Object parameter) throws ValidatorException;
}
