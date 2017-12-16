package org.cx.game.rule;

import org.cx.game.intercepter.IIntercepter;

/**
 * 游戏规则
 * @author chenxian
 *
 */
public interface IRule extends IIntercepter {

	/**
	 * 需要添加规则的对象的接口；所有Interceptable都会单独定义接口，因为拦截器需要Decorator模式
	 * 上面的说法有误，IAction
	 * 只有Class是唯一的；
	 * @return 接口类型
	 */
	public Class getInterceptable();
	
	public void setOwner(Object owner);
	
	public Object getOwner();

}
