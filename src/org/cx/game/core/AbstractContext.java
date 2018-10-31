package org.cx.game.core;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.cx.game.action.ActionProxyHelper;
import org.cx.game.action.IAction;
import org.cx.game.corps.AbstractCorps;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.widget.AbstractControlQueue;
import org.cx.game.widget.AbstractGround;

public abstract class AbstractContext {
	
	public static final Integer Status_Prepare = 0;    //准备状态
	public static final Integer Status_Ready = 1;    //准备就绪，等待开始
	public static final Integer Status_Start = 2;      //开始状态
	
	private String playNo = null;
	private Integer status = Status_Start;
	
	private AbstractPlayState playState = null;
	
	private AbstractPlayer controlPlayer = null;
	private AbstractCorps controlCorps = null;
	private AbstractGround ground = null;
	
	private List<AbstractPlayer> playerList = new ArrayList<AbstractPlayer>();
	
	public AbstractContext(String playNo, AbstractGround ground) {
		// TODO Auto-generated constructor stub
		this.playNo = playNo;           //比赛唯一编号
		
		this.ground = ground;
	}
	
	/**
	 * 编号，用于保存比赛进度
	 * @return
	 */
	public String getPlayNo() {
		return playNo;
	}
	
	/**
	 * 环境状态，当Context刚被创建时，处于Prepare状态，这时游戏规则不起作用，玩家做一些开战前的准备工作；
	 * 当游戏开始后，处于Start状态，这时游戏规则起作用；
	 * @return
	 */
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setPlayState(AbstractPlayState playState) {
		this.playState = playState;
		this.playState.setContext(this);
	}
	
	//---------------- Player -----------------
	
	/**
	 * 用于增加中立部队
	 * @param player 
	 */
	public void addPlayer(AbstractPlayer player) {
		// TODO Auto-generated method stub
		this.playerList.add(player);
		player.setContext(this);
	}
	
	/**
	 * 阵营玩家和非阵营玩家结束后，都会被移除；
	 * @param player
	 */
	public void removePlayer(AbstractPlayer player) {
		// TODO Auto-generated method stub
		this.playerList.remove(player);
	}
	
	/**
	 * 所有player，包含中立player
	 * @return
	 */
	public List<AbstractPlayer> getPlayerList() {
		// TODO Auto-generated method stub
		return this.playerList;
	}
	
	/**
	 * 当前操作比赛的玩家对象
	 */
	public AbstractPlayer getControlPlayer() {
		return controlPlayer;
	}

	public void setControlPlayer(AbstractPlayer controlPlayer) {
		this.controlPlayer = controlPlayer;
	}
	
	/**
	 * 根据troop查找player
	 */
	public AbstractPlayer getPlayer(Integer troop) {
		// TODO Auto-generated method stub
		for(AbstractPlayer player : getPlayerList()){
			if(troop.equals(player.getTroop()))
				return player;
		}
		return null;
	}
	
	//------------------ Corps ---------------
	
	public AbstractCorps getControlCorps() {
		return controlCorps;
	}
	
	public void setControlCorps(AbstractCorps controlCorps) {
		controlCorps.activate(true);
		this.controlCorps = controlCorps;
		
		setControlPlayer(controlCorps.getPlayer());
	}
	
	//------------------ Ground --------------
	
	/**
	 * 
	 * @return 当前ground
	 */
	public AbstractGround getGround() {
		// TODO Auto-generated method stub
		return this.ground;
	}
	
	/**
	 * 根据id查找对应的ground
	 * @param id mapId
	 * @return
	 */
	public abstract AbstractGround getGround(Integer id);
	
	/**
	 * 切换当前ground，因为有area的存在
	 * @param ground
	 */
	public void setGround(AbstractGround ground) {
		// TODO Auto-generated method stub
		this.ground = ground;
	}
	
	//------------------- Bout ------------------
	
	/**
	 * 游戏分为公共回合和玩家回合，公共回合 = 玩家数 * 玩家回合
	
	public abstract Integer getBout();
	
	public abstract IAction getAddBoutAction();
	
	public void addBout() {
		// TODO Auto-generated method stub
		IAction action = new ActionProxyHelper(getAddBoutAction());
		action.action();
	} */
	
	/**
	 * 当前天数，从游戏开始算起
	 * @return
	
	public abstract Integer getDay();
	
	public abstract IAction getAddDayAction();
	
	public void addDay() {
		// TODO Auto-generated method stub
		IAction action = new ActionProxyHelper(getAddDayAction());
		action.action();
	} */
	
	/**
	 * 周
	 * @return
	
	public abstract Integer getWeek();
	
	public abstract IAction getAddWeekAction();
	
	public void addWeek() {
		// TODO Auto-generated method stub
		IAction action = new ActionProxyHelper(getAddWeekAction());
		action.action();
	} */
	
	//------------------- Bout End --------------------

	/**
	 * 比赛开始
	 */
	public void start(){
		setStatus(Status_Start);
		this.playState.start();
	}
	
	/**
	 * 部署
	 */
	public void deploy(){
		this.playState.deploy();
	}
	
	/**
	 * 操作完毕
	 */
	public void done(){
		this.playState.done();
	}
	
	/**
	 * 游戏结束
	 */
	public void finish(){
		this.playState.finish();
	}
	
	/**
	 * 交换比赛控制权
	 * @throws RuleValidatorException 
	 */
	public void switchControl() {
		
		Object object = this.ground.getQueue().out();
		
		if (object instanceof AbstractPlayer) {
			AbstractPlayer player = (AbstractPlayer) object;
			setControlPlayer(player);
		}else{
			AbstractCorps corps = (AbstractCorps) object;
			setControlCorps(corps);
		}
	}
}
