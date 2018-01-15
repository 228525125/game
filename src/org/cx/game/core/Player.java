package org.cx.game.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.cx.game.action.Action;
import org.cx.game.action.IAction;
import org.cx.game.action.IDeath;
import org.cx.game.command.CommandBuffer;
import org.cx.game.corps.Corps;
import org.cx.game.magic.skill.IActiveSkill;
import org.cx.game.magic.skill.ISkill;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.observer.Observable;
import org.cx.game.out.ResponseFactory;
import org.cx.game.policy.DonePolicy;
import org.cx.game.policy.PolicyGroupFactory;
import org.cx.game.policy.IPolicyGroup;
import org.cx.game.policy.IPolicy;
import org.cx.game.policy.NeutralPolicyGroup;
import org.cx.game.rule.RuleGroupFactory;
import org.cx.game.tools.Debug;
import org.cx.game.widget.IGround;
import org.cx.game.widget.building.IBuilding;
import org.cx.game.widget.building.IOption;
import org.cx.game.widget.treasure.IResource;
import org.cx.game.widget.treasure.Resource;

public class Player extends java.util.Observable implements IPlayer ,Observable{
	
	private Integer id = 0;    //这里的id不是玩家的唯一标号，它根据比赛中的位置来定的，仅针对一场比赛是唯一
	
	private IResource resource = null;
	
	public Player(Integer id, String name) {
		// TODO Auto-generated constructor stub
		addObserver(ResponseFactory.getResponse());
		
		this.id = id;
		this.name = name;
		
		this.groupPolicy = PolicyGroupFactory.getInstance(10450001);
		this.groupPolicy.setOwner(this);
		
		this.resource = new Resource(0, 0, 0, 0);
	}
	
	@Override
	public Integer getId() {
		return id;
	}
	
	private String name = null;

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
	
	private Boolean isComputer = false;
	
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
	
	private Integer heroPosition = null;

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
	
	private List<Corps> heroList = new ArrayList<Corps>();
	
	@Override
	public void addHero(Corps hero) {
		// TODO Auto-generated method stub
		this.heroList.add(hero);
	}
	
	@Override
	public List<Corps> getHeroList() {
		// TODO Auto-generated method stub
		return this.heroList;
	}
	
	/*private Map<String,Object> data = new HashMap<String,Object>();
	
	@Override
	public Object getObject(String type) {
		// TODO Auto-generated method stub
		return data.get(type);
	}*/
	
	private CommandBuffer commandBuffer = null;
	
	@Override
	public CommandBuffer getCommandBuffer() {
		// TODO Auto-generated method stub
		return commandBuffer;
	}
	
	private IContext context;

	@Override
	public IContext getContext() {
		// TODO Auto-generated method stub
		return context;
	}
	
	@Override
	public void setContext(IContext context) {
		// TODO Auto-generated method stub
		this.context = context;
		
		//command会用到
		//data.put(CommandBuffer.GROUND, this.ground);
		//data.put(CommandBuffer.OWN, this);
		
		commandBuffer = new CommandBuffer(this);
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
	
	private List<Integer> heroIDList = new ArrayList<Integer>();
	
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
	public List<Corps> getAttendantList(Integer status) {
		// TODO Auto-generated method stub
		return getContext().getGround().list(this, status);
	}
	
	@Override
	public List<Corps> getAttendantList(Boolean activate) {
		// TODO Auto-generated method stub
		List<Corps> list = new ArrayList<Corps>();
		for(Corps corps : getAttendantList(IDeath.Status_Live)){
			if(activate.equals(corps.getActivate().getActivation()))
				list.add(corps);
		}
		return list;
	}
	
	private Integer rationLimit = 10;
	
	@Override
	public Integer getRationLimit() {
		return rationLimit;
	}
	
	@Override
	public void setRationLimit(Integer ration) {
		// TODO Auto-generated method stub
		this.rationLimit = ration;
	}
	
	private Integer ration = 0;
	
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
	
	private Integer bout = 0;
	
	@Override
	public Integer getBout() {
		// TODO Auto-generated method stub
		return bout;
	}
	
	private IAction addBoutAction = null;
	
	public IAction getAddBoutAction(){
		if(null==this.addBoutAction){
			this.addBoutAction = new PlayerAddBout();
			addBoutAction.setOwner(this);
		}
		return this.addBoutAction;
	}
	
	public void addBout() throws RuleValidatorException{
		getAddBoutAction().execute();
	}
	
	private IPolicyGroup groupPolicy = null;
	
	/**
	 * 使用AI自动操作
	 */
	public void automation(){
		while (this.equals(getContext().getControlPlayer())) {
			IPolicy policy = this.groupPolicy.getPolicy();
			if(null!=policy){
				policy.execute();
				
				if(policy instanceof DonePolicy)
					break;
			}
			else
				break;
		}
	}
	
	public class PlayerAddBout extends Action implements IAction {
		
		@Override
		public void action(Object... objects) throws RuleValidatorException {
			// TODO Auto-generated method stub
			Player.this.bout++;
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", Player.this);
			map.put("bout", Player.this.bout);
			NotifyInfo info = new NotifyInfo(NotifyInfo.Player_Bout,map);
			notifyObservers(info);
			
			/*
			 * 获得控制权的玩家单位被激活
			 */
			for(Corps corps : getAttendantList(IDeath.Status_Live)){
				Integer speed = corps.getActivate().getSpeed();
				corps.getActivate().addToVigour(speed);
				try {
					corps.activate(true);
				} catch (RuleValidatorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			/*
			 * 计算技能冷却
			 */
			List<Corps> lList = getAttendantList(IDeath.Status_Live);
			for(Corps corps : lList){
				for(ISkill skill : corps.getSkillList()){
					if (skill instanceof IActiveSkill) {
						IActiveSkill as = (IActiveSkill) skill;
						Integer cooldown = as.getCooldownRemain();
						cooldown = cooldown>0 ? --cooldown : 0;
						as.setCooldownRemain(cooldown);
					}
				}
			}
		}
		
		@Override
		public IPlayer getOwner() {
			// TODO Auto-generated method stub
			return (IPlayer) super.getOwner();
		}
	}
}
