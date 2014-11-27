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
	
	public void addIntercepter(IIntercepter intercepter);
	
	public void deleteIntercepter(IIntercepter intercepter);
	
	public void clear();
}
