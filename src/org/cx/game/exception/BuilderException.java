package org.cx.game.exception;

public class BuilderException extends Exception {

	public BuilderException() {
		// TODO Auto-generated constructor stub
		super("加载比赛对象时异常！");
	}
	
	public BuilderException(String description) {
		// TODO Auto-generated constructor stub
		super("加载比赛对象时异常！"+description);
	}
}
