package org.cx.game.core;

import java.util.ArrayList;
import java.util.List;

import org.cx.game.action.ActionProxyHelper;
import org.cx.game.action.IAction;
import org.cx.game.command.CommandBuffer;
import org.cx.game.corps.AbstractCorps;
import org.cx.game.tools.Util;
import org.cx.game.widget.treasure.Mineral;

public abstract class AbstractPlayer {
	
	private Integer troop = 0;
	private String name = null;
	private Boolean isComputer = false;
	private Integer rationLimit = 10;
	private Integer ration = 0;
	
	private Mineral mineral = null;
	private AbstractContext context = null;
	private CommandBuffer commandBuffer = null;
	
	private List<AbstractCorps> corpsList = new ArrayList<AbstractCorps>();
	
	public AbstractPlayer(Integer id, String name) {
		// TODO Auto-generated constructor stub
		this.troop = id;
		this.name = name;
		
		this.mineral = new Mineral();
		
		commandBuffer = new CommandBuffer(this);
	}

	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
	
	public Boolean isComputer() {
		// TODO Auto-generated method stub
		return this.isComputer;
	}
	
	public void setIsComputer(Boolean isComputer) {
		// TODO Auto-generated method stub
		this.isComputer = isComputer;
	}
	
	public CommandBuffer getCommandBuffer() {
		// TODO Auto-generated method stub
		return commandBuffer;
	}

	public AbstractContext getContext() {
		// TODO Auto-generated method stub
		return context;
	}
	
	public void setContext(AbstractContext context) {
		// TODO Auto-generated method stub
		this.context = context;
	}
	
	/**
	 * 阵营，它根据比赛中的位置来定的
	 * @return
	 */
	public Integer getTroop() {
		return troop;
	}
	
	public void setTroop(Integer troop) {
		// TODO Auto-generated method stub
		this.troop = troop;
	}
	
	/**
	 * 资源
	 * @return
	 */
	public Mineral getMineral() {
		return this.mineral;
	}
	
	/**
	 * 用于xml配置
	 * @param res
	 */
	public void setMineral(Mineral mineral) {
		this.mineral = mineral;
	}
	
	public void setMineral(Integer funType, Mineral mineral) {
		this.mineral = (Mineral) Util.operating(funType, this.mineral, mineral);
	}
	
	/**
	 * 队伍总数限制
	 * @return
	 */
	public Integer getRationLimit() {
		return rationLimit;
	}

	public void setRationLimit(Integer ration) {
		// TODO Auto-generated method stub
		this.rationLimit = ration;
	}
	
	/**
	 * 队伍总数
	 * @return
	 */
	public Integer getRation() {
		// TODO Auto-generated method stub
		return ration;
	}
	
	public void setRation(Integer funType, Integer ration) {
		this.ration = Util.operating(funType, this.ration, ration);
	}
	
	/**
	 * player控制的corps
	 * @return
	 */
	public List<AbstractCorps> getCorpsList() {
		return corpsList;
	}
	
	/**
	 * 游戏分为公共回合和玩家回合，公共回合 = 玩家数 * 玩家回合
	 */
	public abstract Integer getBout();
	
	public abstract IAction getAddBoutAction();
	
	public void addBout() {
		IAction action = new ActionProxyHelper(getAddBoutAction());
		action.action();
	}
	
	/**
	 * 使用AI自动操作
	 */
	public abstract void automation();
	
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if (arg0 instanceof AbstractPlayer) {
			AbstractPlayer player = (AbstractPlayer) arg0;
			return player.getTroop().equals(getTroop());
		}
		return super.equals(arg0);
	}
}
