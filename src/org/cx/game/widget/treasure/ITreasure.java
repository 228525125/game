package org.cx.game.widget.treasure;

import org.cx.game.action.IPicked;
import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;

/**
 * 物品
 * @author chenxian
 *
 */
public interface ITreasure {
	
	public String getName();
	
	public IPicked getPicked();
	
	/**
	 * 被捡起来
	 */
	public void picked(LifeCard life) throws RuleValidatorException;
	
	/**
	 * 在地图上的位置
	 * @return
	 */
	public Integer getPosition();
	
	public void setPosition(Integer position);
}
