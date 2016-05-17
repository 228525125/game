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
import org.cx.game.widget.IUseCard;
import org.cx.game.widget.UseCard;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Context extends Observable implements IContext
{	
	private Map<String,List<IIntercepter>> intercepterList = new HashMap<String,List<IIntercepter>>();
	private String playNo = UUID.randomUUID().toString() ;           //比赛唯一编号
	private IControlQueue queue = new ControlQueue();
	private Long newCardPlayId = 1l;
	
	private final static Integer Power_Add = 100;
	
	private ContextDecorator decorator = null; 
	
	private final static Map<Integer,Map<Integer,Integer>> Attack_Armour = new HashMap<Integer,Map<Integer,Integer>>();
	private final static Map<String,Integer> Magic_Style = new HashMap<String,Integer>();
	private final static Map<String,Integer> Magic_Function = new HashMap<String,Integer>();
	private final static Map<String,Integer> Magic_Hostility = new HashMap<String,Integer>();
	private final static Map<Integer,Integer> Life_Stirps = new HashMap<Integer,Integer>();
	
	public Context(IPlayer player1, IPlayer player2) {
		// TODO Auto-generated constructor stub
		this.player1 = player1;
		this.player2 = player2;
		
		addObserver(new JsonOut());
		
		queue = new ControlQueueDecorator(this.queue);
		queue.add(player1);
		queue.add(player2);
		
		loadResource();
	}
	
	private void loadResource(){
		
		/*
		 * attack.xml
		 */
		Element type = getRoot("attack.path").element("attackType");
		for(Iterator it = type.elementIterator("attack");it.hasNext();){
			Element attack = (Element) it.next();
			Integer attackType = Integer.valueOf(attack.attribute("type").getText());
			
			Map<Integer,Integer> map = new HashMap<Integer,Integer>();
			
			for(Iterator itr = attack.elementIterator("armour");itr.hasNext();){
				Element armour = (Element) itr.next();
				map.put(Integer.valueOf(armour.attribute("type").getText()), Integer.valueOf(armour.getText()));
			}
			
			Attack_Armour.put(attackType, map);
		}
		
		
		/*
		 * magic_style.xml
		 */
		Element magicStyle = getRoot("magic_style.path").element("magicStyle");
		for(Iterator it = magicStyle.elementIterator("style");it.hasNext();){
			Element style = (Element) it.next();
			Integer code = Integer.valueOf(style.attribute("code").getText());
			
			for(Iterator itr = style.elementIterator("magic");itr.hasNext();){
				Element magic = (Element) itr.next();
				String className = magic.attribute("type").getText();
				Magic_Style.put(className, code);
			}
		}
		
		/*
		 * magic_hostility.xml
		 */
		Element magicHostility = getRoot("magic_hostility.path").element("magicHostility");
		for(Iterator it = magicHostility.elementIterator("hostility");it.hasNext();){
			Element hostility = (Element) it.next();
			Integer code = Integer.valueOf(hostility.attribute("code").getText());
			
			for(Iterator itr = hostility.elementIterator("magic");itr.hasNext();){
				Element magic = (Element) itr.next();
				String className = magic.attribute("type").getText();
				Magic_Hostility.put(className, code);
			}
		}
		
		/*
		 * magic_function.xml
		 */
		Element magicFunction = getRoot("magic_function.path").element("magicFunction");
		for(Iterator it = magicFunction.elementIterator("function");it.hasNext();){
			Element function = (Element) it.next();
			Integer code = Integer.valueOf(function.attribute("code").getText());
			
			for(Iterator itr = function.elementIterator("magic");itr.hasNext();){
				Element magic = (Element) itr.next();
				String className = magic.attribute("type").getText();
				Magic_Function.put(className, code);
			}
		}
		
		
		/*
		 * life_stirps
		 */
		Element lifeStirps = getRoot("life_stirps.path").element("lifeStirps");
		for(Iterator it = lifeStirps.elementIterator("stirps");it.hasNext();){
			Element stirps = (Element) it.next();
			Integer code = Integer.valueOf(stirps.attribute("code").getText());
			
			for(Iterator itr = stirps.elementIterator("life");itr.hasNext();){
				Element life = (Element) itr.next();
				Integer cardID = Integer.valueOf(life.attribute("cardID").getText());
				Life_Stirps.put(cardID, code);
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
	 * 
	 * @param atkType 攻击类型
	 * @param armourType 防御类型
	 * @return 折算比例
	 */
	public static Integer getAttackArmour(Integer atkType, Integer armourType){
		Map<Integer,Integer> map = Attack_Armour.get(atkType);
		return map.get(armourType);
	}
	
	/**
	 * 根据一个magic类，获取它的style
	 * @param className 类的全名（包含包名）
	 * @return
	 */
	public static Integer getMagicStyle(String className){
		return Magic_Style.get(className);
	}
	
	/**
	 * 根据style，查询className的集合
	 * @param style 
	 * @return
	 */
	public static List<String> queryMagicStyle(Integer style){
		List<String> list = new ArrayList<String>();
		
		Set<Entry<String,Integer>> set = Magic_Style.entrySet();
		Iterator<Entry<String,Integer>> it = set.iterator();
		while (it.hasNext()) {
			Entry<String, Integer> entry = it.next();
			if(style.equals(entry.getValue()))
				list.add(entry.getKey());
		}
		return list;
	}
	
	/**
	 * 根据一个magic类，获取它的hostility
	 * @param className 类的全名（包含包名）
	 * @return
	 */
	public static Integer getMagicHostility(String className){
		return Magic_Hostility.get(className);
	}
	
	/**
	 * 根据host，查询className的集合
	 * @param host 
	 * @return
	 */
	public static List<String> queryMagicHostility(Integer host){
		List<String> list = new ArrayList<String>();
		
		Set<Entry<String,Integer>> set = Magic_Hostility.entrySet();
		Iterator<Entry<String,Integer>> it = set.iterator();
		while (it.hasNext()) {
			Entry<String, Integer> entry = it.next();
			if(host.equals(entry.getValue()))
				list.add(entry.getKey());
		}
		return list;
	}
	
	/**
	 * 根据一个magic类，获取它的function
	 * @param className 类的全名（包含包名）
	 * @return
	 */
	public static Integer getMagicFunction(String className){
		return Magic_Function.get(className);
	}
	
	/**
	 * 根据func，查询className的集合
	 * @param func 功能
	 * @return
	 */
	public static List<String> queryMagicFunction(Integer func){
		List<String> list = new ArrayList<String>();
		
		Set<Entry<String,Integer>> set = Magic_Function.entrySet();
		Iterator<Entry<String,Integer>> it = set.iterator();
		while (it.hasNext()) {
			Entry<String, Integer> entry = it.next();
			if(func.equals(entry.getValue()))
				list.add(entry.getKey());
		}
		return list;
	}
	
	/**
	 * 根据一个CardID类，获取它的stirps
	 * @param CardID 类的全名（包含包名）
	 * @return
	 */
	public static Integer getLifeStirps(Integer cardID){
		return Life_Stirps.get(cardID);
	}
	
	/**
	 * 根据stirps，查询CardID的集合
	 * @param stirps 功能
	 * @return
	 */
	public static List<Integer> queryLifeStirps(Integer stirps){
		List<Integer> list = new ArrayList<Integer>();
		
		Set<Entry<Integer,Integer>> set = Life_Stirps.entrySet();
		Iterator<Entry<Integer,Integer>> it = set.iterator();
		while (it.hasNext()) {
			Entry<Integer, Integer> entry = it.next();
			if(stirps.equals(entry.getValue()))
				list.add(entry.getKey());
		}
		return list;
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
		map.put("position", getControlLife().getContainerPosition());
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
			getControlPlayer().addToResource(Power_Add);        //增加能量
			
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
		/*List<IIntercepter> list = intercepterList.get(intercepter.getIntercepterMethod());
		if(null!=list){
			list.remove(intercepter);
		}*/
		
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
