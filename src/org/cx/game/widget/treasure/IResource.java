package org.cx.game.widget.treasure;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public interface IResource {

	public Integer get(Integer type);
	
	public void add(IResource resource);
	
	/**
	 * 执行条件 get(type)=null 或 0!=resValue
	 * @param type
	 * @param resValue
	 */
	public void add(Integer type, Integer resValue);
	
	public Set<Entry<Integer, Integer>> entrySet();
	
	public Boolean isEmpty();
	
	/**
	 * 绝对值小于，例如判断CallConsume时，会用到；
	 * @param res
	 * @return
	 */
	public Boolean absoluteLessThan(IResource res);
}
