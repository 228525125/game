package org.cx.game.action;

import org.cx.game.card.MagicCard;

public interface IApply extends IAction {
	
	/**
	 * 消耗能量
	 * @return
	 */
	public Integer getConsume();
	
	public void setConsume(Integer consume);
}
