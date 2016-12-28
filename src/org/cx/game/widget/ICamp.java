package org.cx.game.widget;

import java.util.List;

import org.cx.game.core.IPlayer;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.observer.Observable;

/**
 * 营地，英雄进入战场的位置
 * @author chenxian
 *
 */
public interface ICamp {
	
	public IPlace getOwner();
	
	public Integer getPosition();
	
	public IPlayer getPlayer();
	
	public void setPlayer(IPlayer player);
	
	public void setOwner(IPlace place);
}
