package org.cx.game.widget;

import org.cx.game.core.IPlayer;

/**
 * 营地，等于战场的入口
 * @author chenxian
 *
 */
public interface ICamp {

	public final static Integer Call_Range = 3;
	
	public Integer getHp();
	
	public Double getImmuneDamageRatio();
	
	public IPlace getOwner();
	
	public Integer getPosition();
	
	public IPlayer getPlayer();
	
	public void setPlayer(IPlayer player);
	
	public void setOwner(IPlace place);
}
