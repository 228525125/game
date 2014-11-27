package org.cx.game.builder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.cx.game.exception.BuilderException;

public class ObjectTypeBuilder implements IBuilder {
	
	private List<Map<String, Class>> propertyMethodList = new ArrayList<Map<String, Class>>();
	private List propertyValueList = new ArrayList();
	private List<Class> constructParameterClassList = new ArrayList<Class>();
	private List<Object> constructParameterList = new ArrayList<Object>();
	private String className = null;
	
	@Override
	public Object builder() throws BuilderException {
		// TODO Auto-generated method stub
		Class clazz;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new BuilderException("没有找到“"+className+"”对象!");
		}
		Object result = null;
		
		/*
		 * 创建对象
		 */
		Class[] classArray = new Class[constructParameterClassList.size()]; 
		Constructor con;
		try {
			con = clazz.getConstructor(constructParameterClassList.toArray(classArray));
			result = con.newInstance(constructParameterList.toArray());			
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			throw new BuilderException("“"+className+"”对象,在构造时发生异常！"+e.getMessage());
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			throw new BuilderException("“"+className+"”对象,在构造时发生异常！"+e.getMessage());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			throw new BuilderException("“"+className+"”对象,在构造时发生异常！"+e.getMessage());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new BuilderException("“"+className+"”对象,在构造时发生异常！"+e.getMessage());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			throw new BuilderException("“"+className+"”对象,在构造时发生异常！"+e.getMessage());
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			throw new BuilderException("“"+className+"”对象,在构造时发生异常！"+e.getMessage());
		}
		
		/*
		 * 属性赋值
		 */		
		for(int i=0;i<propertyMethodList.size();i++){
			Map<String, Class> map = propertyMethodList.get(i);
			Entry<String, Class> entry = map.entrySet().iterator().next();
			String methodName = entry.getKey();
			Class clz = entry.getValue();
			Object paramValue = propertyValueList.get(i);
			Method method;
			try {
				method = result.getClass().getDeclaredMethod(methodName,clz);
				method.invoke(result, paramValue);
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				throw new BuilderException("“"+className+"”对象,在对属性赋值时异常！"+e.getMessage());
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				throw new BuilderException("“"+className+"”对象,在对属性赋值时异常！"+e.getMessage());
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				throw new BuilderException("“"+className+"”对象,在对属性赋值时异常！"+e.getMessage());
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				throw new BuilderException("“"+className+"”对象,在对属性赋值时异常！"+e.getMessage());
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				throw new BuilderException("“"+className+"”对象,在对属性赋值时异常！"+e.getMessage());
			}			
		}
		
		return result;
	}
	
	public void setClassName(String className){
		this.className = className;
	}
	
	public void set(String method, Class clazz, Object property){
		Map<String, Class> map = new HashMap<String, Class>();
		map.put(method, clazz);
		propertyMethodList.add(map);
		propertyValueList.add(property);
	}
	
	public void setConstructParameter(Class clazz, Object parameter){
		constructParameterClassList.add(clazz);
		constructParameterList.add(parameter);
	}
}
