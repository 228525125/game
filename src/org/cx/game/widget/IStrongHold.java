package org.cx.game.widget;

import org.cx.game.core.IPlayer;

/**
 *  据点
 * @author chenxian
 *
 */
public interface IStrongHold {

	public void setPlayer(IPlayer player);
	
	public IPlayer getPlayer();

	public Integer getPosition();

	public Integer getRange();

	public Integer getBout();

	public void refurbish();

}