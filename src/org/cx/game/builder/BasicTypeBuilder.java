package org.cx.game.builder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.cx.game.exception.BuilderException;

public class BasicTypeBuilder implements IBuilder {

	private Class clazz = null;
	private String value = null;
	
	@Override
	public Object builder() throws BuilderException {
		// TODO Auto-generated method stub
		Constructor constructor;
		try {
			constructor = clazz.getConstructor(String.class);
			return constructor.newInstance(value);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			throw new BuilderException("创建基本类型时发生异常！"+e.getMessage());
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			throw new BuilderException("创建基本类型时发生异常！"+e.getMessage());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			throw new BuilderException("创建基本类型时发生异常！"+e.getMessage());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new BuilderException("创建基本类型时发生异常！"+e.getMessage());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			throw new BuilderException("创建基本类型时发生异常！"+e.getMessage());
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			throw new BuilderException("创建基本类型时发生异常！"+e.getMessage());
		}
	}

	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
