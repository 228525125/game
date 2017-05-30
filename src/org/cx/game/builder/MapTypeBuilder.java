package org.cx.game.builder;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.cx.game.exception.BuilderException;

public class MapTypeBuilder implements IBuilder {

	private String className = null;
	private String mapType = null;
	private Map<Object, Object> map = new HashMap<Object, Object>();
	
	@Override
	public Map builder() throws BuilderException {
		// TODO Auto-generated method stub
		Object object = null;
		try {
			Class clazz = Class.forName(className);
			object = clazz.getConstructor(null).newInstance(null);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Map m = ((Map) object);
		m.putAll(this.map);
		
		return m;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setMapType(String mapType) {
		this.mapType = mapType;
	}

	public void put(Object key, Object value){
		map.put(key, value);
	}

}
