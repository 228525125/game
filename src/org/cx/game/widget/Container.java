package org.cx.game.widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.cx.game.card.ICard;
import org.cx.game.core.IPlayer;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.JsonOut;
import org.cx.game.rule.RuleGroupFactory;

public abstract class Container extends Observable implements IContainer {

	private Map<String,List<IIntercepter>> intercepterList = new HashMap<String,List<IIntercepter>>();
	protected List<ICard> cardList = new ArrayList<ICard>();
	private IPlayer player;
	private String action;
	
	public static final String CardGroup = "CardGroup";
	public static final String Cemetery = "Cemetery";
	public static final String Ground = "Ground";
	public static final String UseCard = "UseCard";
	public static final String TrickList = "TrickList";
	
	public Container() {
		// TODO Auto-generated constructor stub
		addObserver(JsonOut.getInstance());
		addObserver(RuleGroupFactory.getRuleGroup());
	}
	
	private ContainerDecorator decorator = null;

	public void setDecorator(ContainerDecorator decorator) {
		this.decorator = decorator;
	}

	public ContainerDecorator getDecorator() {
		return decorator;
	}



	public IPlayer getPlayer() {
		return player;
	}

	public void setPlayer(IPlayer player) {
		this.player = player;
	}

	public void setAction(String action) {
		this.action = action;
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

	@Override
	public ICard getCard(Integer position) {
		// TODO Auto-generated method stub
		if(cardList.size()<=position)   //防止超出边界
			return null;
		return cardList.get(position);
	}

	@Override
	public Integer getPosition(ICard card) {
		// TODO Auto-generated method stub
		return cardList.indexOf(card);
	}

	@Override
	public Boolean remove(ICard card) {
		// TODO Auto-generated method stub
		Integer position = card.getContainerPosition();
		Boolean ret = cardList.remove(card);
		if(ret){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", card.getPlayer());
			map.put("container", this);
			map.put("card", card);
			map.put("position", position);
			NotifyInfo info = new NotifyInfo(action,map);
	
			notifyObservers(info);    //通知观察者
		}
		
		return ret;
	}

	@Override
	public Integer getSize() {
		// TODO Auto-generated method stub
		return cardList.size();
	}
	
	@Override
	public List<ICard> list() {
		// TODO Auto-generated method stub
		List<ICard> list = new ArrayList<ICard>();
		list.addAll(this.cardList);
		return list;
	}
	
	@Override
	public List<ICard> listForID(List<Integer> ids) {
		// TODO Auto-generated method stub
		List<ICard> ret = new ArrayList<ICard>();
		for(ICard card : cardList){
			if(ids.contains(card.getId()))
				ret.add(card);
		}
		return ret;
	}
	
	@Override
	public List<ICard> listForID(IPlayer player, List<Integer> ids) {
		// TODO Auto-generated method stub
		List<ICard> ret = new ArrayList<ICard>();
		for(ICard card : cardList){
			if(ids.contains(card.getId()) && player.equals(card.getPlayer()))
				ret.add(card);
		}
		return ret;
	}

	@Override
	public void add(Integer position, ICard card) {
		// TODO Auto-generated method stub
		if(NotifyInfo.Container_Ground_Add.equals(action) || NotifyInfo.Container_Ground_Remove.equals(action))  //如果是ground，position就没有意义
			cardList.add(card);
		else
			cardList.add(position, card);
		
		card.setContainer(decorator);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", card.getPlayer());
		map.put("container", this);
		map.put("card", card);
		map.put("position", position);
		NotifyInfo info = new NotifyInfo(action,map);
		notifyObservers(info);    //通知观察者
	}
	
	@Override
	public void notifyObservers(Object arg0) {
		// TODO Auto-generated method stub
		super.setChanged();
		super.notifyObservers(arg0);
	}
	
	@Override
	public Boolean contains(ICard card) {
		// TODO Auto-generated method stub
		return cardList.contains(card);
	}
	
	public List toList(){
		return cardList;
	}

}
