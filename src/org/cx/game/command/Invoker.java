package org.cx.game.command;

import java.util.List;

import org.cx.game.core.IPlayer;
import org.cx.game.exception.CommandValidatorException;
import org.cx.game.exception.SyntaxValidatorException;
import org.cx.game.exception.ValidatorException;
import org.cx.game.out.JsonOut;
import org.cx.game.out.Response;

public class Invoker {

	private Command command;

	private void setCommand(Command command) {
		this.command = command;
	}
	
	private void action() throws ValidatorException {
		this.command.execute();
	}
	
	/*
	 * 把执行中观察的结果反馈给前台
	 */
	private String response(){
		String response = Response.process.get().toString();
		Response.process.get().delete(0, Response.process.get().length());
		return response;
	}
	
	public String receiveCommand(IPlayer player,String cmd) throws ValidatorException {
		List<InteriorCommand> list = CommandFactory.createCommands(player,cmd);
		for(Command command : list){
			setCommand(command);
			action();
		}
				
		return response();
	}
	
	public String receiveCommand(String cmd, IExternalCommand external) throws ValidatorException {
		List<OutsideCommand> list = CommandFactory.createCommands(cmd, external);
		for(OutsideCommand command : list){
			command.setExternal(external);
			setCommand(command);
			action();
		}
		
		return response();
	}
}
