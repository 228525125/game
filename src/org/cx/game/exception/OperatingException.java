package org.cx.game.exception;

public class OperatingException extends Exception {

	public OperatingException() {
		// TODO Auto-generated constructor stub
		super("计算异常");
	}
	
	public OperatingException(String description) {
		super("计算异常"+description);
	}
}
