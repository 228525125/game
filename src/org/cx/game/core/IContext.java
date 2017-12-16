package org.cx.game.core;

import java.util.List;

import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.observer.Observable;
import org.cx.game.widget.IControlQueue;
import org.cx.game.widget.IGround;

public interface IContext extends Observable {

	public final static String Ground = "Ground";
	public final static String ControlPlayer = "ControlPlayer";
	
	public abstract IControlQueue getControlQueue();

	public abstract void setControlQueue(IControlQueue queue);

	public final static StartState startState = new StartState();
	public final static DeployState deployState = new DeployState();
	public final static DoneState doneState = new DoneState();
	public final static FinishState finishState = new FinishState();

	public abstract void setPlayState(PlayState playState);

	/**
	 * 比赛开始
	 * @throws RuleValidatorException 
	 */
	public abstract void start() throws RuleValidatorException;

	/**
	 *  玩家部署
	 * @throws RuleValidatorException 
	 *
	 */
	public abstract void deploy() throws RuleValidatorException;

	/**
	 *  回合结束
	 * @throws RuleValidatorException 
	 *
	 */
	public abstract void done() throws RuleValidatorException;

	/**
	 * 比赛结束
	 * @throws RuleValidatorException 
	 */
	public abstract void finish() throws RuleValidatorException;
	
	/**
	 * 当前天数，从游戏开始算起
	 * @return
	 */
	public Integer getDay();
	
	public void addDay() throws RuleValidatorException;
	
	/**
	 * 周
	 * @return
	 */
	public Integer getWeek();
	
	public void addWeek() throws RuleValidatorException; 

	public abstract int getBout();

	/**
	 * 当玩家开始部署时，回合数加1
	 * @throws RuleValidatorException 
	 *
	 */
	public abstract void addBout() throws RuleValidatorException;

	//public abstract LifeCard getControlLife(); 半回合制
	
	public List<IPlayer> getPlayerList();

	public IPlayer getControlPlayer();
	
	public void setControlPlayer(IPlayer player);

	/**
	 * 交换比赛控制权
	 * @throws RuleValidatorException 
	 */
	public abstract void switchControl() throws RuleValidatorException;

	public abstract String getPlayNo();
	
	/**
	 * 生成一个唯一的cardId
	 * @return
	 */
	public Long newCardPlayId();
	
	public IGround getGround();

}