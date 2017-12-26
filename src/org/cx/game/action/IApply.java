package org.cx.game.action;

import java.util.Map;

import org.cx.game.card.MagicCard;
import org.cx.game.widget.treasure.IResource;

public interface IApply extends IAction {
	
	/**
	 * 消耗资源
	 * @return
	 */
	public IResource getConsume();
	
	public void setConsume(IResource consume);
	
	public MagicCard getOwner();
}
