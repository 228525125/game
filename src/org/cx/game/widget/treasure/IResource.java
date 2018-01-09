package org.cx.game.widget.treasure;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public interface IResource {
	
	public final static Integer Gold = 701;                    //金币
	
	public final static Integer Wood = 702;                    //木材
	
	public final static Integer Stone = 703;                   //石材
	
	public final static Integer Ore = 704;                     //矿石
	
	public final static Integer EmpiricValue = 710;             //经验值
	
	public final static Integer SkillCount = 720;              //技能点

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
