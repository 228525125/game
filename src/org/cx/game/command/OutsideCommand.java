package org.cx.game.command;

import org.cx.game.core.IPlayer;
import org.cx.game.exception.CommandValidatorException;
import org.cx.game.exception.ValidatorException;

public class OutsideCommand extends Command {

	private IExternalCommand external = null;
	
	public OutsideCommand() {
		super();
		// TODO Auto-generated constructor stub		
	}
	
	@Override
	public void execute() throws ValidatorException {
		// TODO Auto-generated method stub
		super.execute();		
		
		external.execute(parameter);
	}
	
	public void setExternal(IExternalCommand external) {
		this.external = external;
	}

}
