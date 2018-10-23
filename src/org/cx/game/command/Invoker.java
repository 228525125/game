package org.cx.game.command;

import org.cx.game.command.expression.Calculator;
import org.cx.game.core.Camera;
import org.cx.game.core.AbstractPlayer;
import org.cx.game.core.Record;
import org.cx.game.exception.SyntaxValidatorException;
import org.cx.game.exception.ValidatorException;
import org.cx.game.out.AbstractResponse;

public class Invoker {
	
	private String response = "";
	private String playNo = "";
	
	private Command command = null;
	private AbstractPlayer player = null;
	
	public Invoker(String playNo) {
		// TODO Auto-generated constructor stub
		this.playNo = playNo;
	}

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
		response = AbstractResponse.process.get().toString();
		AbstractResponse.process.get().delete(0, AbstractResponse.process.get().length());
		
		record();
	}
	
	/**
	 * 将观察结果记录下来
	 */
	private void record(){
		Camera camera = Camera.getInstance();
		
		if(!"".equals(response) && 0<response.split(";").length){
			String[] resps = response.split(";");
			
			Integer sequence = camera.getNewSequence();
			for(int i=0;i<resps.length;i++){
				Record r = new Record();
				r.setPlayNo(playNo);
				r.setResponse(resps[i]);
				r.setExecutor(player.getTroop());
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
	
	public synchronized void receiveCommand(AbstractPlayer player,String cmd) throws ValidatorException {
		this.player = player;
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
}
