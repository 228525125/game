package org.cx.game.intercepter;

/**
 * 拦截器接口，添加拦截器时需要实现该接口
 * @author chenxian
 *
 */
public interface IIntercepter {
	
	/**
	 * 拦截哪一个方法
	 * @return
	 */
	public String getIntercepterMethod();

	/**
	 * 方法执行前
	 * @param args
	 */
	public void before(Object [] args);
	
	/**
	 * 拦截结束前执行(不管被拦截的方法是否被调用，该方法都会被执行)
	 * @param args
	 */
	public void finish(Object [] args);
	
	/**
	 * 方法后执行（仅被拦截方法被调用后，才会被执行）
	 * @param args
	 */
	public void after(Object [] args);
	
	/**
	 * 是否要执行拦截的方法
	 * @return
	 */
	public Boolean isInvoke();
	
	/**
	 * 默认顺序
	 */
	public static final Integer Order_Default = 100;
	
	/**
	 * 拦截器执行的选后顺序，数值越小优先级越高
	 * @return
	 */
	public Integer getOrder();
	
	public static final Integer Level_System = 1;
	
	public static final Integer Level_Rule = 2;
	
	public static final Integer Level_Current = 3;
	
	/**
	 * 拦截器在系统中的级别
	 * @return
	 */
	public Integer getLevel();
	
	/*public void delete();
	
	public Boolean isDelete();*/
}
