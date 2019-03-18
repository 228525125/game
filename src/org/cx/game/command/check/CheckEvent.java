package org.cx.game.command.check;

public class CheckEvent extends Event {
	
	private String method = null;

	private Object [] arguments = null;
	
	public CheckEvent(Object source, String method, Object [] arguments) {
		super(source);
		// TODO Auto-generated constructor stub
		this.method = method;
		this.arguments = arguments;
	}
	
	public Object[] getArguments() {
		return arguments;
	}
	
	public String getMethod() {
		return method;
	}

}
