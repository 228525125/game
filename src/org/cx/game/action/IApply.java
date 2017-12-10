package org.cx.game.action;

import java.util.Map;

import org.cx.game.card.MagicCard;

public interface IApply extends IAction {
	
	/**
	 * 消耗资源
	 * @return
	 */
	public Map<String,Integer> getConsume();
	
	public void setConsume(Map<String,Integer> consume);
	
	public MagicCard getOwner();
}
