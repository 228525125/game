package org.cx.game.intercepter;

/**
 * 用于回收拦截器的接口
 * @author chenxian
 *
 */
public interface IRecover {

	/**
	 * 记录拦截器的流向，便于回收
	 * @param interceptable
	 * @param intercepter
	 */
	public void recordIntercepter(IInterceptable interceptable, IIntercepter intercepter);
	
	/**
	 * 清除拦截器
	 * @param level 拦截器级别
	 */
	public void resetIntercepter(Integer level);
}
