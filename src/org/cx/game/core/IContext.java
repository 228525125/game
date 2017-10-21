package org.cx.game.core;

import java.util.List;

import org.cx.game.card.LifeCard;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.observer.Observable;
import org.cx.game.widget.IControlQueue;

public interface IContext extends IInterceptable,Observable {

	public abstract IControlQueue getControlQueue();

	public abstract void setControlQueue(IControlQueue queue);
	
	public void setDecorator(ContextDecorator decorator);

	public final static StartState startState = new StartState();
	public final static DeployState deployState = new DeployState();
	public final static DoneState doneState = new DoneState();
	public final static FinishState finishState = new FinishState();

	public abstract void setPlayState(PlayState playState);

	/**
	 * 比赛开始
	 */
	public abstract void start();

	/**
	 *  玩家部署
	 *
	 */
	public abstract void deploy();

	/**
	 *  回合结束
	 *
	 */
	public abstract void done();

	/**
	 * 比赛结束
	 */
	public abstract void finish();

	public abstract int getBout();

	/**
	 * 当玩家开始部署时，回合数加1
	 *
	 */
	public abstract void addBout();

	//public abstract LifeCard getControlLife(); 半回合制
	
	public List<IPlayer> getPlayerList();

	public IPlayer getControlPlayer();
	
	public void setControlPlayer(IPlayer player);

	/**
	 * 交换比赛控制权
	 */
	public abstract void switchControl();

	public abstract String getPlayNo();
	
	/**
	 * 生成一个唯一的cardId
	 * @return
	 */
	public Long newCardPlayId();

}