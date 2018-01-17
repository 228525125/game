package org.cx.game.widget.treasure;

import org.cx.game.action.IAction;
import org.cx.game.corps.AbstractCorps;
import org.cx.game.exception.RuleValidatorException;

/**
 * 物品
 * @author chenxian
 *
 */
public interface ITreasure {
	
	public String getName();
	
	public IAction getPicked();
	
	/**
	 * 被捡起来
	 */
	public void picked(AbstractCorps corps) throws RuleValidatorException;
	
	/**
	 * 在地图上的位置
	 * @return
	 */
	public Integer getPosition();
	
	public void setPosition(Integer position);
}
