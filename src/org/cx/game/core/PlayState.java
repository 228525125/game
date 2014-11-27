package org.cx.game.core;

import java.util.Observable;

public abstract class PlayState extends Observable implements org.cx.game.observer.Observable{

	protected Context context;

	public void setContext(Context context) {
		this.context = context;
	}
	
	/**
	 * 比赛开始
	 */
	public abstract void start();
	
	/**
	 * 玩家部署
	 */
	public abstract void deploy();
	
	/**
	 * 回合结束
	 */
	public abstract void done();
	
	/**
	 * 比赛结束
	 */
	public abstract void finish();
	
	@Override
	public void notifyObservers(Object arg0) {
		// TODO Auto-generated method stub
		super.setChanged();
		super.notifyObservers(arg0);
	}
}
