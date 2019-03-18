package org.cx.game.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.cx.game.core.IPlayer;

public class CommandBuffer {

	private Object cacheObject = null;
	private IPlayer player = null;
	private Stack<Object> stack = null;
	private Map<String, Object> parameter = null;
	
	public CommandBuffer(IPlayer player) {
		// TODO Auto-generated constructor stub
		this.player = player;
		stack = new Stack<Object>();
		this.cacheObject = player;
		parameter = new HashMap<String, Object>();
	}
	
	public IPlayer getPlayer() {
		return player;
	}
	
	public void set(Object object) {
		stack.push(this.cacheObject);
		this.cacheObject = object;
	}
	
	public Object get() {
		return this.cacheObject;
	}
	
	public void backspace() {
		if(this.stack.isEmpty())
			this.cacheObject = this.player;
		else
			this.cacheObject = this.stack.pop();
	}
	
	public void setParameter(String parameterName, Object parameter) {
		this.parameter.put(parameterName, parameter);
	}
	
	public Object getParameter(String parameterName) {
		return this.parameter.get(parameterName);
	}
}
