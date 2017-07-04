package org.cx.game.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.command.CommandBuffer;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.observer.Observable;
import org.cx.game.out.JsonOut;
import org.cx.game.policy.IPlayerPolicy;
import org.cx.game.policy.PlayerPolicy;
import org.cx.game.tools.Debug;
import org.cx.game.widget.CardGroup;
import org.cx.game.widget.CardGroupDecorator;
import org.cx.game.widget.ICardGroup;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IUseCard;
import org.cx.game.widget.UseCard;
import org.cx.game.widget.UseCardDecorator;

public class Player extends java.util.Observable implements IPlayer ,Observable{
	
	private Integer id = 0;    //这里的id不是玩家的唯一标号，它根据比赛中的位置来定的，仅针对一场比赛是唯一
	
	private Map<String,List<IIntercepter>> intercepterList = new HashMap<String,List<IIntercepter>>();	
	
	public Player(Integer id, String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.id = id;
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
	
	private Integer heroEntry = null;

	@Override
	public Integer getHeroEntry() {
		// TODO Auto-generated method stub
		return heroEntry;
	}

	@Override
	public void setHeroEntry(Integer position) {
		// TODO Auto-generated method stub
		this.heroEntry = position;
	}

	private IGround ground = null;
	
	@Override
	public IGround getGround() {
		// TODO Auto-generated method stub
		return ground;
	}
	
	public void setGround(IGround ground) {
		this.ground = ground;
	}
	
	private IUseCard useCard = null;
	
	@Override
	public IUseCard getUseCard() {
		// TODO Auto-generated method stub
		return useCard;
	}
	
	private Map<String,Object> data = new HashMap<String,Object>();
	
	@Override
	public Object getObject(String type) {
		// TODO Auto-generated method stub
		return data.get(type);
	}
	
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
		addObserver(new JsonOut());
		
		this.context = context;
		
		//command会用到
		data.put(CommandBuffer.GROUND, this.ground);
		data.put(CommandBuffer.OWN, this);
		data.put(CommandBuffer.OTHER, context.getOtherPlayer(this));
		
		commandBuffer = new CommandBuffer(this);
	}
	
	private Integer resource = 0;

	@Override
	public Integer getResource() {
		// TODO Auto-generated method stub
		if(Debug.isDebug)
			return Debug.power;
		return resource;
	}
	
	@Override
	public void setResource(Integer resource) {
		// TODO Auto-generated method stub
		this.resource = resource;
	}
	
	@Override
	public void addToResource(Integer power) {
		// TODO Auto-generated method stub
		if(0!=power){
			this.resource += power;
			this.resource = this.resource>0 ? this.resource : 0;    //判断下限
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", this);
			map.put("resource", this.resource);
			map.put("change", power);
			NotifyInfo info = new NotifyInfo(NotifyInfo.Player_Resource,map);
			notifyObservers(info);
		}
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
	
	private IPlayerPolicy policy = new PlayerPolicy(this); 
	
	@Override
	public IPlayerPolicy getPolicy() {
		// TODO Auto-generated method stub
		return policy;
	}
	
	private LifeCard hero;
	
	public LifeCard getHero() {
		// TODO Auto-generated method stub
		return this.hero;
	}
	public void setHero(LifeCard hero) {
		// TODO Auto-generated method stub
		this.hero = hero;
	}
	
	private Integer heroCardID = null;
	
	@Override
	public Integer getHeroCardID() {
		// TODO Auto-generated method stub
		return this.heroCardID;
	}
	
	@Override
	public void setHeroCardID(Integer cardID) {
		// TODO Auto-generated method stub
		this.heroCardID = cardID;
	}
	
	private Integer callCountPlay = 0;                        //玩家本次比赛召唤随从次数
	private Integer callCountBout = 0;                        //玩家本回合召唤随从次数
	
	@Override
	public Integer getCallCountOfBout() {
		// TODO Auto-generated method stub
		return callCountBout;
	}
	
	@Override
	public void addCallCountOfBout(Integer time) {
		// TODO Auto-generated method stub
		callCountBout += time;
	}

	@Override
	public void resetCallCountOfBout() {
		// TODO Auto-generated method stub
		callCountBout = 0;
	}
	
	@Override
	public Integer getCallCountOfPlay() {
		// TODO Auto-generated method stub
		return callCountPlay;
	}
	
	@Override
	public void addCallCountOfPlay(Integer time) {
		// TODO Auto-generated method stub
		callCountPlay += time;
	}
	
	@Override
	public List<LifeCard> getAttendantList() {
		// TODO Auto-generated method stub
		return getGround().list(this);
	}
	
	@Override
	public List<LifeCard> getAttendantList(Boolean activate) {
		// TODO Auto-generated method stub
		List<LifeCard> list = new ArrayList<LifeCard>();
		for(LifeCard life : getAttendantList()){
			if(activate.equals(life.getActivate().getActivation()))
				list.add(life);
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
	
	@Override
	public void addBout() {
		// TODO Auto-generated method stub
		this.bout++;
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", this);
		map.put("bout", this.bout);
		NotifyInfo info = new NotifyInfo(NotifyInfo.Player_Bout,map);
		notifyObservers(info);
	}
	
	@Override
	public void addIntercepter(IIntercepter intercepter) {
		// TODO Auto-generated method stub
		List<IIntercepter> intercepters = intercepterList.get(intercepter.getIntercepterMethod());
		if(null!=intercepters){
			intercepters.add(intercepter);
		}else{
			intercepters = new ArrayList<IIntercepter>();
			intercepters.add(intercepter);
			intercepterList.put(intercepter.getIntercepterMethod(), intercepters);
		}
	}

	@Override
	public void deleteIntercepter(IIntercepter intercepter) {
		// TODO Auto-generated method stub
		intercepter.delete();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		intercepterList.clear();
	}

	@Override
	public Map<String,List<IIntercepter>> getIntercepterList() {
		// TODO Auto-generated method stub
		return intercepterList;
	}
}
