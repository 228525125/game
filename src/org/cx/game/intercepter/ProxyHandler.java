package org.cx.game.intercepter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ProxyHandler implements InvocationHandler {

	private IInterceptable target;
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		Map<String,List<IIntercepter>> intercepterList = target.getIntercepterList();
		List<IIntercepter> intercepters = intercepterList.get(method.getName());
		if(null!=intercepters){
			
			/*
			 * before
			 */
			Object result = null;
			Boolean invoke = true;
			Collections.sort(intercepters, new IntercepterAscComparator());   //正序
			for(IIntercepter intercepter : intercepters){
				intercepter.before(args);
				if(invoke&&!intercepter.isInvoke())
					invoke = false;
			}
			
			if(invoke){
				result = method.invoke(target, args);
				
				/*
				 * after
				 */
				Collections.sort(intercepters, new IntercepterDescComparator());   //反序
				for(IIntercepter intercepter : intercepters){
					intercepter.after(args);
				}
			}
			
			/*
			 * finish
			 */
			Collections.sort(intercepters, new IntercepterDescComparator());   //反序
			for(IIntercepter intercepter : intercepters){
				intercepter.finish(args);
			}
			
			return result;
		}
		return method.invoke(target, args);
	}
	
	public void setTarget(IInterceptable target) {  
        this.target = target;  
    }

}
