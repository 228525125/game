package org.cx.game.action;

import java.util.Map;

import org.cx.game.widget.treasure.IResource;

/**
 * 升级
 * @author chenxian
 *
 */
public interface IUpgrade extends IAction {

	public Integer getLevel();
	
	public void setLevel(Integer level);
	
	public Integer getLevelLimit();
	
	public void setLevelLimit(Integer levelLimit);
	
	/**
	 * 升级需要耗费的资源数[类型][数量]
	 * @return
	 */
	public IResource getRequirement();
}
