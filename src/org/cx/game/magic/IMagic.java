package org.cx.game.magic;

import org.cx.game.tag.ITag;

/**
 * 所有魔法的公共接口（trick、skill、buff）
 * @author 贤
 *
 */
public interface IMagic extends ITag {

	/**
	 * 
	 * @return 唯一编号
	 */
	public Integer getType();
	
	/**
	 * 所属对象
	 * @return
	 */
	public Object getOwner();
	
	/**
	 * 是否启动法术效果，因为有些法术是针对特定对象的，如果对象出错效果将取消，
	 * 但法术动作不会取消
	 * @param args 效果作用单位
	 * @return
	 */
	public Boolean isTrigger(Object[] args);
	
	/**
	 * 影响，发挥作用，尽量把法术的逻辑写在这个方法里面，因为Affected会直接调用这个方法,
	 * 如果是buff，根据需要来写，例如中毒，每回合都会发生影响的，就要把效果写在这个方法
	 * 里面
	 * @param objects
	 */
	public void affect(Object...objects);		
	
	/**
	 * 描述
	 * @return
	 */
	public String getDepiction();
}
