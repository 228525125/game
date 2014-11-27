package org.cx.game.widget;

import org.cx.game.core.IPlayer;

/**
 * 营地，等于战场的入口
 * @author chenxian
 *
 */
public interface ICamp {

	public final static Integer Call_Range = 3;
	
	public Integer getLife();
	
	public Integer getDef();
	
	public IPlace getOwner();
	
	public IPlayer getPlayer();
	
	public void setPlayer(IPlayer player);
}
