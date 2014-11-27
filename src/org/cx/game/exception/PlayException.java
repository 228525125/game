package org.cx.game.exception;

public class PlayException extends Exception {

	public PlayException() {
		// TODO Auto-generated constructor stub
		super("比赛异常！");
	}
	
	public PlayException(String description){
		super("比赛异常！"+description);
	}
}
