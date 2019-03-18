package org.cx.game.magic.trick;

import org.cx.game.core.IPlayer;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.IRecover;
import org.cx.game.magic.IMagic;
import org.cx.game.observer.Observable;
import org.cx.game.widget.ITrickList;

public interface ITrick extends IInterceptable,Observable,IIntercepter,IMagic,IRecover {
	
	public ITrickList getOwner();
	
	public IPlayer getPlayer();
	
	final static Integer Setup_Range = 1;
	
	final static Integer Setup_Bout = 10;
	
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
