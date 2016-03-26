package org.cx.game.intercepter;

public abstract class Intercepter implements IIntercepter {

	private String method = "";
	
	/**
	 * method默认为：action
	 */
	public Intercepter(){
		this.method = "action";
	}
	
	public Intercepter(String method) {
		// TODO Auto-generated constructor stub
		this.method = method;
	}
	
	@Override
	public void finish(Object[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void before(Object[] args) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void after(Object[] args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getIntercepterMethod() {
		// TODO Auto-generated method stub
		return method;
	}

	@Override
	public Integer getOrder() {
		// TODO Auto-generated method stub
		return IIntercepter.Order_Default;
	}

	@Override
	public Boolean isInvoke() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public Integer getLevel() {
		// TODO Auto-generated method stub
		return IIntercepter.Level_Current;
	}
	
	private Boolean isDelete = false;
	
	@Override
	public void delete() {
		// TODO Auto-generated method stub
		this.isDelete = true;
	}
	
	@Override
	public Boolean isDelete() {
		// TODO Auto-generated method stub
		return isDelete;
	}

}
