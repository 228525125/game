package org.cx.game.card.skill;

import org.cx.game.core.IPlayer;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.IRecover;
import org.cx.game.observer.Observable;
import org.cx.game.widget.IContainer;
import org.cx.game.widget.IPlace;
import org.cx.game.widget.ITrickList;

public interface ITrick extends IInterceptable,Observable,IIntercepter,IMagic,IRecover {
	
	public ITrickList getOwner();
	
	public IPlayer getPlayer();
	
	public final static Integer Setup_Range = 1;
	
	public final static Integer Setup_Bout = 10;
	
	/**
	 * 安装
	 */
	public void setup();
	
	/**
	 * 失效，与effect对应
	 */
	public void invalid();
	
	/**
	 * 持续回合
	 * @return
	 */
	public Integer getBout();
	
	public void setBout(Integer bout);
	
	public String getAction();
}
