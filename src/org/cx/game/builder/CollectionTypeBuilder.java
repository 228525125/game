package org.cx.game.builder;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.cx.game.exception.BuilderException;

public class CollectionTypeBuilder implements IBuilder {

	private String className = null;
	private String collectionType = null;
	private List<Object> list = new ArrayList<Object>();
	
	@Override
	public Object builder() throws BuilderException {
		// TODO Auto-generated method stub
		Object object = null;
		try {
			Class clazz = Class.forName(className);
			object = clazz.getConstructor(null).newInstance(null);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new BuilderException("没有找到“"+className+"”对象!");
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			throw new BuilderException("构造“"+className+"”对象时异常!"+e.getMessage());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new BuilderException("构造“"+className+"”对象时异常!"+e.getMessage());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			throw new BuilderException("构造“"+className+"”对象时异常!"+e.getMessage());
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			throw new BuilderException("构造“"+className+"”对象时异常!"+e.getMessage());
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			throw new BuilderException("构造“"+className+"”对象时异常!"+e.getMessage());
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			throw new BuilderException("构造“"+className+"”对象时异常!"+e.getMessage());
		}
		
		if("java.util.List".equals(collectionType)){
			((List) object).addAll(list);
		}
		
		return object;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setCollectionType(String collectionType) {
		this.collectionType = collectionType;
	}

	public void add(Object o){
		list.add(o);
	}
}
