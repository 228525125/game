package org.cx.game.core;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Set;
import java.util.UUID;

import org.cx.game.action.Death;
import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.command.CommandBuffer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.IntercepterAscComparator;
import org.cx.game.npc.NPC;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.JsonOut;
import org.cx.game.rule.RuleGroupFactory;
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
	
	private ContextDecorator decorator = null; 
	
	private final static Map<Integer,Integer> TagCategory_1 = new HashMap<Integer,Integer>();
	private final static Map<Integer,List<Integer>> TagCategory_2 = new HashMap<Integer,List<Integer>>();
	private final static Map<Integer,List<Integer>> Tag_1 = new HashMap<Integer,List<Integer>>();
	private final static Map<Integer,List<Integer>> Tag_2 = new HashMap<Integer,List<Integer>>();
	
	private int bout = 0;  //回合
	
	private Integer day = 0; //天
	
	private Integer week = 0; //星期几
	
	private List<IPlayer> playerList = new ArrayList<IPlayer>();
	
	private IPlayer controlPlayer=null;
	private IGround ground = null;
	
	public Context(IGround ground, IPlayer[] players) {
		// TODO Auto-generated constructor stub
		for(int i=0;i<players.length;i++){
			playerList.add(players[i]);
		}
		
		for(IPlayer player : playerList){
			this.queue.add(player);
		}
		
		this.ground = ground;
		
		addObserver(JsonOut.getInstance());
		
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
				for(Iterator iter = tag.elementIterator("object");iter.hasNext();){
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
	 * 根据对象，查询tag
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
	
	/**
	 * 游戏分为公共回合和玩家回合，公共回合 = 玩家数 * 玩家回合
	 */
	public int getBout() {
		return bout;
	}
	
	public void addBout(){
		bout++;
		if(1==bout%getPlayerList().size()){
			decorator.addDay();
			if(1==day%7)
				decorator.addWeek();
		}
	}
	
	@Override
	public Integer getDay() {
		// TODO Auto-generated method stub
		return day;
	}
	
	@Override
	public void addDay() {
		// TODO Auto-generated method stub
		this.day++;
	}
	
	@Override
	public Integer getWeek() {
		// TODO Auto-generated method stub
		return this.week;
	}
	
	@Override
	public void addWeek() {
		// TODO Auto-generated method stub
		this.week++;
	}
	
	@Override
	public List<IPlayer> getPlayerList() {
		// TODO Auto-generated method stub
		return this.playerList;
	}
	
	/**
	 * 当前操作比赛的玩家对象
	 */
	public IPlayer getControlPlayer() {
		return controlPlayer;
	}

	public void setControlPlayer(IPlayer controlPlayer) {
		this.controlPlayer = controlPlayer;
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("control", controlPlayer);
		NotifyInfo info = new NotifyInfo(NotifyInfo.Context_Control,map);
		notifyObservers(info);
	}
	
	public void switchControl(){
		Object object = queue.out();
		
		setControlPlayer((IPlayer) object);
		
		decorator.addBout();
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

	@Override
	public IGround getGround() {
		// TODO Auto-generated method stub
		return this.ground;
	}
}
