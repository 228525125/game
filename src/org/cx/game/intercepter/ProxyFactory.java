package org.cx.game.intercepter;

import java.lang.reflect.Proxy;

import org.cx.game.card.LifeCard;
import org.cx.game.widget.IContainer;

public class ProxyFactory {

	public static Object getProxy(IInterceptable object){
		ProxyHandler handler = new ProxyHandler();
		handler.setTarget(object);
		// 第一个参数是用来创建动态代理的ClassLoader对象，只要该对象能访问Dog接口即可  
        // 第二个参数是接口数组，正是代理该接口的数组  
        // 第三个参数是代理包含的处理实例  
        return Proxy.newProxyInstance(IInterceptable.class.getClassLoader(), object  
                .getClass().getInterfaces(), handler);  
	}
}
