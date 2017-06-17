package org.cx.game.core;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Set;
import java.util.UUID;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.IntercepterAscComparator;
import org.cx.game.npc.NPC;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.JsonOut;
import org.cx.game.tools.PropertiesUtil;
import org.cx.game.widget.ControlQueue;
import org.cx.game.widget.ControlQueueDecorator;
import org.cx.game.widget.ICardGroup;
import org.cx.game.widget.IControlQueue;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IUseCard;
import org.cx.game.widget.UseCard;
import org.cx.game.widget.building.IBuilding;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Context extends Observable implements IContext
{	
	private Map<String,List<IIntercepter>> intercepterList = new HashMap<String,List<IIntercepter>>();
	private String playNo = UUID.randomUUID().toString() ;           //比赛唯一编号
	private IControlQueue queue = new ControlQueue();
	private Long newCardPlayId = 1l;                          //用于记录本场比赛中生成的id
	
	private final static Integer Bout_Power_Add = 1;               //玩家每回合能量增加单位
	
	private ContextDecorator decorator = null; 
	
	private final static Map<Integer,Integer> TagCategory_1 = new HashMap<Integer,Integer>();
	private final static Map<Integer,List<Integer>> TagCategory_2 = new HashMap<Integer,List<Integer>>();
	private final static Map<Integer,List<Integer>> Tag_1 = new HashMap<Integer,List<Integer>>();
	private final static Map<Integer,List<Integer>> Tag_2 = new HashMap<Integer,List<Integer>>();
	
	
	public Context(IPlayer player1, IPlayer player2) {
		// TODO Auto-generated constructor stub
		this.player1 = player1;
		this.player2 = player2;
		
		this.queue.add(player1);
		this.queue.add(player2);
		
		addObserver(new JsonOut());
		
		loadResource();
	}
	
	private void loadResource(){
		
		/*
		 * tag
		 */
		Element tags = getRoot("tag.path").element("tags");
		for(Iterator it = tags.elementIterator("category");it.hasNext();){
			Element category = (Element) it.next();
			Integer categoryCode = Integer.valueOf(category.attribute("code").getText());
			
			TagCategory_2.put(categoryCode, new ArrayList());
			for(Iterator ite = category.elementIterator("tag");ite.hasNext();){
				Element tag = (Element) ite.next();
				Integer tagCode = Integer.valueOf(tag.attribute("code").getText());
				
				TagCategory_1.put(tagCode, categoryCode);
				TagCategory_2.get(categoryCode).add(tagCode);
				
				Tag_2.put(tagCode, new ArrayList<Integer>());
				for(Iterator iter = category.elementIterator("object");iter.hasNext();){
					Element object = (Element) iter.next();
					Integer objectCode = Integer.valueOf(object.attribute("code").getText());
					
					
					if(null==Tag_1.get(objectCode))
						Tag_1.put(objectCode, new ArrayList<Integer>());
					
					Tag_1.get(objectCode).add(tagCode);
					Tag_2.get(tagCode).add(objectCode);
				}
			}
		}
	}
	
	private static Element getRoot(String pathName) {
		SAXReader saxReader = new SAXReader();
		try {
			InputStream is=new BufferedInputStream(new FileInputStream(PropertiesUtil.getConfigure(pathName))); 
		
			Document document = saxReader.read(is);
			return document.getRootElement();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据对象，查询标签
	 * @param object
	 * @return
	 */
	public static List<Integer> queryForObject(Integer object){
		return Tag_1.get(object);
	}
	
	/**
	 * 根据tag，查询对象	
	 * @param tag
	 * @return
	 */
	public static List<Integer> queryForTag(Integer tag){
		return Tag_2.get(tag);
	}
	
	/**
	 * 根据category，查询tag的集合
	 * @param category
	 * @return
	 */
	public static List<Integer> queryForCategory(Integer category){
		return TagCategory_2.get(category);
	}
	
	/**
	 * 根据tag，查询category
	 * @param category
	 * @return
	 */
	public static Integer getCategory(Integer tag){
		return TagCategory_1.get(tag);
	}
	
	public void setDecorator(ContextDecorator decorator) {
		this.decorator = decorator;
	}

	public IControlQueue getControlQueue() {
		return queue;
	}

	public void setControlQueue(IControlQueue queue) {
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
	private int bout=0;  //游戏回合，
	
	public int getBout() {
		return bout;
	}
	
	public void addBout(){
		bout++;
	}
	
	/** 
	 * 获得行动权的life
	 */
	//private LifeCard controlLife = null;   半回合制
	
	/* 半回合制
	 * public LifeCard getControlLife() {
		return controlLife;
	}*/

	/* 半回合制
	 * private void setControlLife(LifeCard controlLife) {
		this.controlLife = controlLife;
		setControlPlayer(controlLife.getPlayer());
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("control", getControlPlayer());
		map.put("life", getControlLife());
		map.put("position", getControlLife().getContainerPosition());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Context_Control,map);
		notifyObservers(info);
	}*/

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
	private void setControl(IPlayer player){
			setControlPlayer(player);
			
			boutCount += 1;
			if(boutCount/playerNumber==1){
				decorator.addBout();                        //增加回合
				boutCount = 0;
			}
			
			Integer tax = 0;
			IGround ground = player.getGround();
			List<IBuilding> list = ground.getBuildingList(player);
			for(IBuilding building : list)
				tax += building.getTax();
			
			getControlPlayer().addToResource(tax);              //征税
			
			getControlPlayer().resetCallCountOfBout();          //重置call计数器
			
			for(LifeCard life : player.getAttendantList()){
				Integer speed = life.getActivate().getSpeed();
				life.getActivate().addToVigour(speed);
				try {
					life.activate(true);
				} catch (RuleValidatorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//this.controlLife=null;  半回合制    //如果ControlLife不为null就表示控制权在life
		
		/*if (object instanceof LifeCard) {
			LifeCard life = (LifeCard) object;
			try {
				life.activate(true);
			} catch (RuleValidatorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}             //设置为可以行动
			setControlLife(life);
			
			if(life.getHero()){
				boutCount += 1;
				if(boutCount/playerNumber==1){
					decorator.addBout();                        //增加回合
					boutCount = 0;
				}
				
				Integer addPower = getBout()<11 ? getBout() : 10;
				
				getControlPlayer().addToResource(addPower-getControlPlayer().getResource());        //增加能量
				
				getControlPlayer().resetCallCountOfBout();          //重置call计数器
				
				getControlPlayer().takeCard();                  //摸牌
			}
		}*/
	}
	
	public void switchControl(){
		Object object = queue.out();
		setControl((IPlayer) object);
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
