package org.cx.game.core;

import java.util.List;

import org.cx.game.corps.AbstractCorps;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.observer.Observable;
import org.cx.game.widget.IControlQueue;
import org.cx.game.widget.IGround;

public interface IContext extends Observable {

	public final static String Ground = "Ground";
	public final static String ControlPlayer = "ControlPlayer";
	
	public IControlQueue getControlQueue();
	
	public AbstractPlayState getDeployState();
	
	public AbstractPlayState getDoneState();
	
	public AbstractPlayState getStartState();
	
	public AbstractPlayState getFinishState();

	public void setPlayState(AbstractPlayState playState);

	/**
	 * 比赛开始
	 * @throws RuleValidatorException 
	 */
	public void start() throws RuleValidatorException;

	/**
	 *  玩家部署
	 * @throws RuleValidatorException 
	 *
	 */
	public void deploy() throws RuleValidatorException;

	/**
	 *  回合结束
	 * @throws RuleValidatorException 
	 *
	 */
	public void done() throws RuleValidatorException;

	/**
	 * 比赛结束
	 * @throws RuleValidatorException 
	 */
	public void finish() throws RuleValidatorException;
	
	/**
	 * 游戏分为公共回合和玩家回合，公共回合 = 玩家数 * 玩家回合
	 */
	public int getBout();
	
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

	/**
	 * 当玩家开始部署时，回合数加1
	 * @throws RuleValidatorException 
	 *
	 */
	public void addBout() throws RuleValidatorException;
	
	public List<IPlayer> getPlayerList();

	public IPlayer getControlPlayer();
	
	public void setControlPlayer(IPlayer player);

	/**
	 * 交换比赛控制权
	 * @throws RuleValidatorException 
	 */
	public void switchControl() throws RuleValidatorException;
	
	/**
	 * 生成一个唯一的游戏内Id
	 * @return
	 */
	public Long newPlayId();
	
	public String getPlayNo();
	
	public IGround getGround();

}