package org.cx.game.intercepter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Iterator;
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
			
			/*for(IIntercepter intercepter : intercepters){
				intercepter.before(args);
				if(invoke&&!intercepter.isInvoke())
					invoke = false;
			}*/
			
			/*
			 * 为什么要用下面这段代码替换上面的代码，因为下面的代码避免了java.util.ConcurrentModificationException 异常
			 */
			
			Iterator<IIntercepter> it = intercepters.iterator();
			while(it.hasNext()){
				IIntercepter intercepter = it.next();
				if(intercepter.isDelete()){ 
					it.remove();
					continue;
				}
				
				intercepter.before(args);
				
				if(intercepter.isDelete()){ 
					it.remove();
					continue;
				}
				
				if(invoke&&!intercepter.isInvoke())
					invoke = false;
			}
			
			if(invoke){
				result = method.invoke(target, args);
				
				/*
				 * after
				 */
				Collections.sort(intercepters, new IntercepterAscComparator());   //正序
				
				/*for(IIntercepter intercepter : intercepters){
					intercepter.after(args);
				}*/
				
				it = intercepters.iterator();
				while(it.hasNext()){
					IIntercepter intercepter = it.next();
					if(intercepter.isDelete()){ 
						it.remove();
						continue;
					}
					
					intercepter.after(args);
					
					if(intercepter.isDelete()){ 
						it.remove();
						continue;
					}
				}
			}
			
			/*
			 * finish
			 */
			Collections.sort(intercepters, new IntercepterDescComparator());   //反序
			
			/*for(IIntercepter intercepter : intercepters){
				intercepter.finish(args);
			}*/
			
			it = intercepters.iterator();
			while(it.hasNext()){
				IIntercepter intercepter = it.next();
				if(intercepter.isDelete()){ 
					it.remove();
					continue;
				}
				
				intercepter.finish(args);
				
				if(intercepter.isDelete()){ 
					it.remove();
					continue;
				}
			}
			
			return result;
		}
		return method.invoke(target, args);
	}
	
	public void setTarget(IInterceptable target) {  
        this.target = target;  
    }

}
