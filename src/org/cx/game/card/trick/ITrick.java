package org.cx.game.card.trick;

import org.cx.game.card.magic.IMagic;
import org.cx.game.core.IPlayer;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.IRecover;
import org.cx.game.observer.Observable;
import org.cx.game.widget.TrickList;

public interface ITrick extends IInterceptable,Observable,IIntercepter,IMagic,IRecover {
	
	public TrickList getOwner();
	
	public IPlayer getPlayer();
	
	public final static Integer Setup_Range = 1;
	
	public final static Integer Setup_Bout = 10;
	
	public String getName();
	
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
