package org.cx.game.exception;

public class ParseException extends Exception {

	public ParseException() {
		// TODO Auto-generated constructor stub
		super("解析对象时异常！");
	}
	
	public ParseException(String description) {
		// TODO Auto-generated constructor stub
		super("解析对象时异常！"+description);
	}
}
