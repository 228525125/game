package org.cx.game.intercepter;

/**
 * 拦截器，开发者请注意，在before、after、finish方法中，不可再对被拦截对象添加拦截器，
 * 否则将抛出异常java.util.ConcurrentModificationException
 * @author chenxian
 *
 */
public abstract class AbstractIntercepter implements IIntercepter {

	private String method = "";
	
	/**
	 * method默认为：action
	 */
	public AbstractIntercepter(){
		this.method = "action";
	}
	
	public AbstractIntercepter(String method) {
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

}
