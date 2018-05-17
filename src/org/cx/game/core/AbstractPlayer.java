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
import org.cx.game.widget.treasure.IResource;
import org.cx.game.widget.treasure.Resource;

public abstract class AbstractPlayer extends java.util.Observable implements IPlayer ,Observable{
	
	private Integer id = 0;    //这里的id不是玩家的唯一标号，它根据比赛中的位置来定的，仅针对一场比赛是唯一
	private String name = null;
	private Boolean isComputer = false;
	private Integer heroPosition = null;
	private Integer rationLimit = 10;
	private Integer ration = 0;
	
	private IResource resource = null;
	private IContext context = null;
	private CommandBuffer commandBuffer = null;
	
	private List<Integer> heroIDList = new ArrayList<Integer>();
	private List<AbstractCorps> heroList = new ArrayList<AbstractCorps>();
	
	public AbstractPlayer(Integer id, String name) {
		// TODO Auto-generated constructor stub
		addObserver(ResponseFactory.getResponse());
		
		this.id = id;
		this.name = name;
		
		this.resource = new Resource(0, 0, 0, 0);
		
		commandBuffer = new CommandBuffer(this);
	}
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
	
	@Override
	public Boolean getComputer() {
		// TODO Auto-generated method stub
		return this.isComputer;
	}
	
	@Override
	public void setComputer(Boolean isComputer) {
		// TODO Auto-generated method stub
		this.isComputer = isComputer;
	}

	@Override
	public Integer getHomePosition() {
		// TODO Auto-generated method stub
		return heroPosition;
	}

	@Override
	public void setHomePosition(Integer position) {
		// TODO Auto-generated method stub
		this.heroPosition = position;
	}
	
	@Override
	public void addHero(AbstractCorps hero) {
		// TODO Auto-generated method stub
		this.heroList.add(hero);
	}
	
	@Override
	public List<AbstractCorps> getHeroList() {
		// TODO Auto-generated method stub
		return this.heroList;
	}
	
	@Override
	public CommandBuffer getCommandBuffer() {
		// TODO Auto-generated method stub
		return commandBuffer;
	}

	@Override
	public IContext getContext() {
		// TODO Auto-generated method stub
		return context;
	}
	
	@Override
	public void setContext(IContext context) {
		// TODO Auto-generated method stub
		this.context = context;
	}
	
	public IResource getResource() {
		return resource;
	}
	
	@Override
	public void addToResource(IResource res) {
		// TODO Auto-generated method stub
		if(!res.isEmpty()){
			this.resource.add(res);
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", this);
			map.put("resource", this.resource);
			NotifyInfo info = new NotifyInfo(NotifyInfo.Player_Resource,map);
			notifyObservers(info);
		}
	}
	
	@Override
	public void addToResource(Integer resType, Integer res) {
		// TODO Auto-generated method stub
		if(0!=res){
			this.resource.add(resType, res);
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", this);
			map.put("resource", this.resource);
			NotifyInfo info = new NotifyInfo(NotifyInfo.Player_Resource,map);
			notifyObservers(info);
		}
	}
	
	/**
	 * 用于xml配置
	 * @param res
	 */
	public void setResource(IResource res){
		this.resource = res;
	}

	@Override
	public void notifyObservers(Object arg0) {
		// TODO Auto-generated method stub
		super.setChanged();
		super.notifyObservers(arg0);
	}
	
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if (arg0 instanceof IPlayer) {
			IPlayer player = (IPlayer) arg0;
			return player.getId().equals(getId());
		}
		return super.equals(arg0);
	}
	
	@Override
	public List<Integer> getHeroIDList() {
		// TODO Auto-generated method stub
		return this.heroIDList;
	}
	
	@Override
	public void addHeroID(Integer ID) {
		// TODO Auto-generated method stub
		this.heroIDList.add(ID);
	}
	
	@Override
	public Integer getRationLimit() {
		return rationLimit;
	}
	
	@Override
	public void setRationLimit(Integer ration) {
		// TODO Auto-generated method stub
		this.rationLimit = ration;
	}
	
	@Override
	public Integer getRation() {
		// TODO Auto-generated method stub
		return ration;
	}
	
	@Override
	public void addToRation(Integer ration) {
		// TODO Auto-generated method stub
		if(0<ration){
			this.ration += ration;
			this.ration = 0 > this.ration ? 0 : this.ration;			
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", this);
			map.put("ration", this.ration);
			map.put("change", ration);
			NotifyInfo info = new NotifyInfo(NotifyInfo.Player_Ration,map);
			notifyObservers(info);
		}
	}
	
	public void addBout() throws RuleValidatorException{
		IAction action = new ActionProxyHelper(getAddBoutAction());
		action.action();
	}
	
	/**
	 * 使用AI自动操作
	 */
	public abstract void automation();
}
