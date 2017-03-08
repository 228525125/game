package org.cx.game.widget;

import org.cx.game.core.IPlayer;

/**
 * 营地，英雄进入战场的位置
 * @author chenxian
 *
 */
public interface ICamp {
	
	public IPlace getOwner();
	
	public Integer getPosition();
	
	public IPlayer getCampPlayer();
	
	public void setCampPlayer(IPlayer player);
	
	public void setOwner(IPlace place);
}
