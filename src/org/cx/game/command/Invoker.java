package org.cx.game.command;

import org.cx.game.command.expression.Calculator;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.SyntaxValidatorException;
import org.cx.game.exception.ValidatorException;
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
	
	private void intergrityValidate(String cmd) throws SyntaxValidatorException {
		String[] cs = cmd.split(Calculator.SPACE);
		if(cs.length==0)
			throw new SyntaxValidatorException("org.cx.game.command.Invoker.intergrityValidate");
	}
	
	public String receiveCommand(IPlayer player,String cmd) throws ValidatorException {
		intergrityValidate(cmd);    //验证命令完整性
		
		for(String c : cmd.split(";")){
			InteriorCommand command = CommandFactory.createCommand(player,c);
			setCommand(command);
			action();
		}
		
		return response();
	}
	
	public String receiveCommand(String cmd, IExternalCommand external) throws ValidatorException {
		intergrityValidate(cmd);    //验证命令完整性
		
		for(String c : cmd.split(";")){
			OutsideCommand command = CommandFactory.createCommand(c, external);
			setCommand(command);
			action();
		}
		
		return response();
	}
}
