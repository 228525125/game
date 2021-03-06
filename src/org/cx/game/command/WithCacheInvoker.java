package org.cx.game.command;

import org.cx.game.command.expression.Calculator;
import org.cx.game.core.Camera;
import org.cx.game.core.Record;
import org.cx.game.exception.SyntaxValidatorException;
import org.cx.game.exception.ValidatorException;
import org.cx.game.out.AbstractResponse;

public class WithCacheInvoker implements IInvoker {
	
	private String response = "";
	
	private Command command = null;
	private CommandBuffer buffer = null;
	
	/**
	 * 主机已经被创建
	 * @param buffer 缓存了主机、玩家等信息
	 */
	public WithCacheInvoker(CommandBuffer buffer) {
		// TODO Auto-generated constructor stub
		this.buffer = buffer;
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
		
		String playNo = buffer.getPlayer().getHost().getPlayNo();
		
		if(!"".equals(response) && 0<response.split(";").length){
			String[] resps = response.split(";");
			
			Integer sequence = camera.getNewSequence();
			for(int i=0;i<resps.length;i++){
				Record r = new Record();
				r.setPlayNo(playNo);
				r.setResponse(resps[i]);
				r.setExecutor(buffer.getPlayer().getTroop());
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
	
	public synchronized void receiveCommand(String cmd) throws ValidatorException {
		try {
			intergrityValidate(cmd);    //验证命令完整性
			
			for(String c : cmd.split(";")){
				Command command = CommandFactory.getInstance(c, buffer);
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
