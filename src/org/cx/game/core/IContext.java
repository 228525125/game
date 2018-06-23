package org.cx.game.core;

import java.util.List;

import org.cx.game.action.IAction;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.Observable;
import org.cx.game.widget.IGround;

public interface IContext extends Observable {

	final static String ControlPlayer = "ControlPlayer";
	
	public AbstractPlayState getDeployState();
	
	public AbstractPlayState getDoneState();
	
	public AbstractPlayState getStartState();
	
	public AbstractPlayState getFinishState();

	public void setPlayState(AbstractPlayState playState);

	/**
	 * 比赛开始
	 * @throws RuleValidatorException 
	 */
	public void start();

	/**
	 *  玩家部署
	 * @throws RuleValidatorException 
	 *
	 */
	public void deploy();

	/**
	 *  回合结束
	 * @throws RuleValidatorException 
	 *
	 */
	public void done();

	/**
	 * 比赛结束
	 * @throws RuleValidatorException 
	 */
	public void finish();
	
	public IAction getAddBoutAction();
	
	/**
	 * 游戏分为公共回合和玩家回合，公共回合 = 玩家数 * 玩家回合
	 */
	public int getBout();
	
	/**
	 * 当玩家开始部署时，回合数加1
	 * @throws RuleValidatorException 
	 *
	 */
	public void addBout();
	
	public IAction getAddDayAction();
	
	/**
	 * 当前天数，从游戏开始算起
	 * @return
	 */
	public Integer getDay();
	
	public void addDay();
	
	public IAction getAddWeekAction();
	
	/**
	 * 周
	 * @return
	 */
	public Integer getWeek();
	
	public void addWeek();
	
	/**
	 * 用于增加中立部队
	 * @param player 
	 */
	public void addPlayer(IPlayer player);
	
	/**
	 * 阵营玩家和非阵营玩家结束后，都会被移除；
	 * @param player
	 */
	public void removePlayer(IPlayer player);
	
	/**
	 * 所有player，包含中立player
	 * @return
	 */
	public List<IPlayer> getPlayerList();

	public IPlayer getControlPlayer();
	
	public void setControlPlayer(IPlayer player);

	/**
	 * 交换比赛控制权
	 * @throws RuleValidatorException 
	 */
	public void switchControl();
	
	public String getPlayNo();
	
	/**
	 * 根据id查找对应的ground
	 * @param id mapId
	 * @return
	 */
	public IGround getGround(Integer id);
	
	/**
	 * 
	 * @return 当前ground
	 */
	public IGround getGround();
	
	/**
	 * 切换当前ground，因为有area的存在
	 * @param ground
	 */
	public void setGround(IGround ground);

}