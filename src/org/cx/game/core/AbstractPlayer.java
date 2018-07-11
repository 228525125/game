package org.cx.game.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cx.game.action.ActionProxyHelper;
import org.cx.game.action.IAction;
import org.cx.game.command.CommandBuffer;
import org.cx.game.corps.AbstractCorps;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.observer.Observable;
import org.cx.game.out.ResponseFactory;
import org.cx.game.tools.CommonIdentifier;
import org.cx.game.widget.treasure.Resource;

public abstract class AbstractPlayer {
	
	private Integer troop = 0;
	private String name = null;
	private Boolean isComputer = false;
	private Integer rationLimit = 10;
	private Integer ration = 0;
	
	private Resource resource = null;
	private AbstractContext context = null;
	private CommandBuffer commandBuffer = null;
	
	public AbstractPlayer(Integer id, String name) {
		// TODO Auto-generated constructor stub
		this.troop = id;
		this.name = name;
		
		this.resource = new Resource(0, 0, 0, 0);
		
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
	public Resource getResource() {
		return resource;
	}
	
	public void addToResource(Resource res) {
		// TODO Auto-generated method stub
		if(!res.isEmpty()){
			this.resource.add(res);
		}
	}
	
	public void addToResource(Integer resType, Integer res) {
		// TODO Auto-generated method stub
		if(0!=res){
			this.resource.add(resType, res);
		}
	}
	
	/**
	 * 用于xml配置
	 * @param res
	 */
	public void setResource(Resource res){
		this.resource = res;
	}
	
	/**
	 * 人口限制
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
	 * 人口总数
	 * @return
	 */
	public Integer getRation() {
		// TODO Auto-generated method stub
		return ration;
	}
	
	public void addToRation(Integer ration) {
		// TODO Auto-generated method stub
		if(0<ration){
			this.ration += ration;
			this.ration = 0 > this.ration ? 0 : this.ration;
		}
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
