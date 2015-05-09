package org.cx.game.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.UUID;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.IntercepterAscComparator;
import org.cx.game.npc.NPC;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.JsonOut;
import org.cx.game.widget.ControlQueue;
import org.cx.game.widget.ControlQueueDecorator;
import org.cx.game.widget.ICardGroup;
import org.cx.game.widget.IControlQueue;
import org.cx.game.widget.IUseCard;
import org.cx.game.widget.UseCard;

public class Context extends Observable implements IContext
{	
	private Map<String,List<IIntercepter>> intercepterList = new HashMap<String,List<IIntercepter>>();
	private String playNo = UUID.randomUUID().toString() ;           //比赛唯一编号
	private IControlQueue queue = new ControlQueue();
	private Long newCardPlayId = 1l;
	
	private ContextDecorator decorator = null; 
	
	public Context(IPlayer player1, IPlayer player2) {
		// TODO Auto-generated constructor stub
		this.player1 = player1;
		this.player2 = player2;
		
		addObserver(new JsonOut());
		
		queue = new ControlQueueDecorator(this.queue);
		queue.add(player1);
		queue.add(player2);
	}
	
	public void setDecorator(ContextDecorator decorator) {
		this.decorator = decorator;
	}

	public IControlQueue getQueue() {
		return queue;
	}

	public void setQueue(IControlQueue queue) {
		this.queue = queue;
	}

	/**
	 * 比赛的各个状态（阶段）
	 */
	private PlayState playState;

	public void setPlayState(PlayState playState) {
		this.playState = playState;
		this.playState.setContext(this);
	}

	public void start(){
		setPlayState(startState);
		this.playState.start();
	}
	
	public void deploy(){
		this.playState.deploy();
	}
	
	public void done(){
		this.playState.done();
	}
	
	public void finish(){
		this.playState.finish();
	}
	
	private IPlayer player1;
	
	public IPlayer getPlayer1() {
		return player1;
	}	

	private IPlayer player2;
	
	public IPlayer getPlayer2() {
		return player2;
	}
	
	private int playerNumber = 2;    //参赛人数
	private int bout=playerNumber;  //游戏回合，
	
	public int getBout() {
		return bout;
	}
	
	public void addBout(){
		bout++;
	}
	
	/**
	 * 获得行动权的life
	 */
	private LifeCard controlLife = null;
	
	public LifeCard getControlLife() {
		return controlLife;
	}

	private void setControlLife(LifeCard controlLife) {
		this.controlLife = controlLife;
		setControlPlayer(controlLife.getPlayer());
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("control", getControlPlayer());
		map.put("life", getControlLife());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Context_Control,map);
		notifyObservers(info);
	}

	/**
	 * 当前操作比赛的玩家对象
	 */
	private IPlayer controlPlayer=null;
	
	public IPlayer getControlPlayer() {
		return controlPlayer;
	}

	private void setControlPlayer(IPlayer controlPlayer) {
		this.controlPlayer = controlPlayer;
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("control", controlPlayer);
		map.put("life", null);
		NotifyInfo info = new NotifyInfo(NotifyInfo.Context_Control,map);
		notifyObservers(info);
	}
	
	public IPlayer getOtherPlayer(IPlayer player){
		if(player.getId()==player1.getId())
			return player2;
		else
			return player1;
	}
	
	private Integer boutCount = 1;   //用于回合数的计算
	
	/**
	 * 切换控制权
	 * @param object
	 */
	private void setControl(Object object){
		if (object instanceof IPlayer) {
			IPlayer control = (IPlayer) object;
			
			setControlPlayer(control);
			
			boutCount += 1;
			if(boutCount/playerNumber==1){
				decorator.addBout();                        //增加回合
				boutCount = 0;
			}
			Integer power = getControlPlayer().getResource();
			getControlPlayer().setResource(++power);        //增加能量
			
			ICardGroup cardGroup = getControlPlayer().getCardGroup();
			ICard card = cardGroup.out();  //摸牌
			
			IUseCard useCard = getControlPlayer().getUseCard();
			useCard.add(useCard.getSize(),card);
			
			this.controlLife=null;      //如果ControlLife不为null就表示控制权在life
		}
		if (object instanceof LifeCard) {
			LifeCard life = (LifeCard) object;
			life.setActivate(true);             //设置为可以行动
			setControlLife(life);
		}
	}
	
	public void switchControl(){
		Object object = queue.out();
		setControl(object);
	}
	
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
		List<IIntercepter> list = intercepterList.get(intercepter.getIntercepterMethod());
		if(null!=list){
			list.remove(intercepter);
		}
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

	public String getPlayNo() {
		return playNo;
	}

	@Override
	public void notifyObservers(Object arg0) {
		// TODO Auto-generated method stub
		super.setChanged();
		super.notifyObservers(arg0);
	}

	@Override
	public Long newCardPlayId() {
		// TODO Auto-generated method stub
		return newCardPlayId++;
	}
}
