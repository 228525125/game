package org.cx.game.tag;

import java.util.List;

public interface ITag {
	
	/**
	 * 是否包含一种标签
	 * @param tag
	 * @return
	 */
	public Boolean contains(Integer tag);
	
	/**
	 * 根据分类查找标签
	 * @param category
	 * @return
	 */
	public List<Integer> queryForCategory(Integer category);
}
