package org.cx.game.widget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.cx.game.card.LifeCard;
import org.cx.game.card.TrickCard;
import org.cx.game.card.skill.ITrick;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.IntercepterAscComparator;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.JsonOut;

public class Place extends Observable implements IPlace {

	private Map<String,List<IIntercepter>> intercepterList = new HashMap<String,List<IIntercepter>>();
	private ITrickList trickList = new TrickList(this);
	private ICemetery cemetery = new Cemetery(this);
	private LifeCard life = null;
	private Integer position = 0;
	private IGround ground = null;
	private ICamp camp = null;
	private Boolean disable = false;
	
	public Place(IGround ground,Integer position) {
		// TODO Auto-generated constructor stub
		this.ground = ground;
		this.position = position;
		this.addObserver(new JsonOut());
	}
	
	public IContainer getContainer(){
		return ground;
	}
	
	@Override
	public void addTrick(ITrick trick) {
		// TODO Auto-generated method stub
		this.trickList.add(trick);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", trick.getPlayer());
		map.put("container", ground);
		map.put("trick", trick);
		map.put("position", position);
		NotifyInfo info = new NotifyInfo(trick.getAction(),map);
		notifyObservers(info);
		
		if (trick instanceof TrickCard) {
			TrickCard trickCard = (TrickCard) trick;
			getContainer().add(position, trickCard);     //没有实际作用，仅仅将card保存到ground
		}
	}
	
	@Override
	public void addCorpse(LifeCard life) {
		// TODO Auto-generated method stub
		this.cemetery.add(life);
		this.disable = false;
	}
	
	@Override
	public void removeCorpse(LifeCard life) {
		// TODO Auto-generated method stub
		this.cemetery.remove(life);
	}

	@Override
	public LifeCard getLife() {
		// TODO Auto-generated method stub
		return life;
	}

	@Override
	public Integer getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public ITrickList getTrickList() {
		// TODO Auto-generated method stub
		return trickList;
	}
	
	private static final String Invalid = "_Invalid";
	
	@Override
	public void removeTrick(ITrick trick) {
		// TODO Auto-generated method stub
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", trick.getPlayer());
		map.put("container", ground);
		map.put("trick", trick);
		map.put("position", position);
		NotifyInfo info = new NotifyInfo(trick.getAction()+Invalid,map);
		notifyObservers(info);
		
		trickList.remove(trick);
	}
	
	@Override
	public ICemetery getCemetery() {
		// TODO Auto-generated method stub
		return cemetery;
	}

	@Override
	public void in(LifeCard life) {
		// TODO Auto-generated method stub
		this.life = life;
		this.disable = true;
		
		//getContainer().add(position, life);          //他的作用是，给life.setContainer
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", life.getPlayer());
		map.put("container", ground);
		map.put("card", life);
		map.put("position", position);
		NotifyInfo info = new NotifyInfo(NotifyInfo.Container_Place_In,map);
		notifyObservers(info);    //通知观察者
	}
	
	@Override
	public LifeCard out() {
		// TODO Auto-generated method stub
		LifeCard life = this.life;
		this.life = null;
		this.disable = false;
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", life.getPlayer());
		map.put("container", ground);
		map.put("card", life);
		map.put("position", position);
		NotifyInfo info = new NotifyInfo(NotifyInfo.Container_Place_Out,map);
		notifyObservers(info);    //通知观察者
		return life;
	}
	
	@Override
	public Integer getStep(IPlace place) {
		// TODO Auto-generated method stub
		return ground.distance(position, place.getPosition());
	}

	@Override
	public ICamp getCamp() {
		// TODO Auto-generated method stub
		return this.camp;
	}

	@Override
	public void setCamp(ICamp camp) {
		// TODO Auto-generated method stub
		this.camp = camp;
	}

	@Override
	public Boolean isDisable() {
		// TODO Auto-generated method stub
		return this.disable;
	}

	@Override
	public void setDisable(Boolean disable) {
		// TODO Auto-generated method stub
		this.disable = disable;
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
	
	@Override
	public void notifyObservers(Object arg0) {
		// TODO Auto-generated method stub
		super.setChanged();
		super.notifyObservers(arg0);
	}
}
