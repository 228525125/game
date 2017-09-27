package org.cx.game.command;

import org.cx.game.command.expression.Calculator;
import org.cx.game.core.Camera;
import org.cx.game.core.ContextFactory;
import org.cx.game.core.IContext;
import org.cx.game.core.IPlayer;
import org.cx.game.core.Record;
import org.cx.game.exception.SyntaxValidatorException;
import org.cx.game.exception.ValidatorException;
import org.cx.game.out.Response;

public class Invoker {

	private Command command;
	private String response = "";

	private void setCommand(Command command) {
		this.command = command;
	}
	
	private void action() throws ValidatorException {
		this.command.execute();
	}
	
	public String getResponse() {		
		return response;
	}

	/*
	 * 把执行中观察的结果反馈给前台
	 */
	private void response(){
		response = Response.process.get().toString();
		Response.process.get().delete(0, Response.process.get().length());
		
		record();
	}
	
	private void record(){
		Camera camera = Camera.getInstance();
		
		if(!"".equals(response) && 0<response.split(";").length){
			String[] resps = response.split(";");
			
			IContext context = ContextFactory.getContext();
			String playNo = context.getPlayNo();
			Integer sequence = camera.getNewSequence();
			for(int i=0;i<resps.length;i++){
				Record r = new Record();
				r.setPlayNo(playNo);
				r.setCommand(resps[i]);
				r.setSequence(sequence+i);
				String action = resps[i].split("\",")[0].substring(11);
				r.setAction(action);
				
				camera.addRecord(r);
			}
		}
	}
	
	private void intergrityValidate(String cmd) throws SyntaxValidatorException {
		String[] cs = cmd.split(Calculator.SPACE);
		if(cs.length==0)
			throw new SyntaxValidatorException("org.cx.game.command.Invoker.intergrityValidate");
	}
	
	public void receiveCommand(IPlayer player,String cmd) throws ValidatorException {
		try {
			intergrityValidate(cmd);    //验证命令完整性
			
			for(String c : cmd.split(";")){
				InteriorCommand command = CommandFactory.getInstance(player,c);
				setCommand(command);
				action();
			}
		} catch (ValidatorException e) {
			// TODO: handle exception
			throw e;
		} finally {
			response();
		}
	}
	
	public void receiveCommand(String cmd, IExternalCommand external) throws ValidatorException {
		try {
			intergrityValidate(cmd);    //验证命令完整性
		
			for(String c : cmd.split(";")){
				OutsideCommand command = CommandFactory.getInstance(c);
				command.setExternal(external);
				setCommand(command);
				action();
			}
		} catch (ValidatorException e) {
			// TODO: handle exception
			throw e;
		} finally {
			response();
		}
	}
}
