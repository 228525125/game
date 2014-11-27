package org.cx.game.out;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.widget.IContainer;

public abstract class Response {
	
	public static ThreadLocal<StringBuffer> process = new ThreadLocal<StringBuffer>(){
		@Override
		protected StringBuffer initialValue() {
			// TODO Auto-generated method stub
			return new StringBuffer();
		}
	};
	
	public Response() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 动作
	 */
	private String action = "";

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	private Object info = null;
	
	public Object getInfo() {
		return info;
	}

	public void setInfo(Object info) {
		this.info = info;
	}

	/**
	 * 把观察到的结果转换成特定对象
	 * @param info 观察结果
	 * @return
	 */
	public abstract Object convert(Response resp);
	
}
