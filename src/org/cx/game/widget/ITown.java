package org.cx.game.widget;


/**
 * 城镇
 * @author chenxian
 *
 */
public interface ITown extends IBuilding{
	
	/**
	 * 城镇的位置
	 * @return
	 */
	public Integer getPosition();
	
	/**
	 * 城镇的等级
	 * @return
	 */
	public Integer getLevel();
	
	public void setLevel(Integer level);
	
	/**
	 * 是否主城
	 * @return
	 */
	public Boolean isMain();
}
