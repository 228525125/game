package org.cx.game.command;

import org.cx.game.exception.ValidatorException;

public interface IInvoker {

	public String getResponse();
	
	public void receiveCommand(String cmd) throws ValidatorException;
}
