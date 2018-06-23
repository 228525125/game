package org.cx.game.observer;

public class NotifyInfo {
	
	private String type;
	private Object info;
	
	public NotifyInfo(String type, Object info) {
		// TODO Auto-generated constructor stub
		this.type = type;
		this.info = info;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Object getInfo() {
		return info;
	}
	public void setInfo(Object info) {
		this.info = info;
	}
}
