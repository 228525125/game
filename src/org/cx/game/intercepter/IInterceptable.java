package org.cx.game.intercepter;

import java.util.List;
import java.util.Map;

/**
 * 被拦截对象接口
 * @author chenxian
 *
 */
public interface IInterceptable {

	public Map<String,List<IIntercepter>> getIntercepterList();
	
	/**
	 * 添加拦截器，实际是缓存起来，并未真正添加
	 * @param intercepter
	 */
	public void addIntercepter(IIntercepter intercepter);
	
	/**
	 * 删除拦截器，实际是缓存起来，并未真正删除
	 * @param intercepter
	 */
	public void deleteIntercepter(IIntercepter intercepter);
	
	public void clear();
}
