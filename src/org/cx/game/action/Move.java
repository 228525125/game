package org.cx.game.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.JsonOut;
import org.cx.game.rule.IRule;
import org.cx.game.rule.MoveRule;
import org.cx.game.tools.Debug;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IPlace;

public class Move extends Action implements IMove{

	private Integer energy = 0;
	private Integer consume = IMove.Consume;        //移动一格的消耗
	private Integer type = Type_Walk;
	private Boolean moveable = false;   //是否能移动，回合内只能移动一次
	private Integer flee = 0;         //逃离成功率
	private Boolean hide = false;           //隐形状态
	
	private MoveRule rule = new MoveRule(this);
	
	public Move() {
		// TODO Auto-generated constructor stub
		//addObserver(rule);         预留，暂没有内容
	}
	
	@Override
	public MoveDecorator getDecorator() {
		// TODO Auto-generated method stub
		return (MoveDecorator) super.getDecorator();
	}
	
	@Override
	public Integer getType() {
		// TODO Auto-generated method stub
		return this.type;
	}
	
	@Override
	public void setType(Integer type) {
		// TODO Auto-generated method stub
		this.type = type;
	}
	
	@Override
	public void changeType(Integer type) {
		// TODO Auto-generated method stub
		if(!this.type.equals(type)){
			this.type = type;
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", getOwner().getPlayer());
			map.put("container", getOwner().getContainer());
			map.put("card", getOwner());
			map.put("change", type);
			map.put("position", getOwner().getContainerPosition());
			NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_State_Type,map);
			super.notifyObservers(info);
		}
	}
	
	public Integer getConsume() {
		return consume;
	}

	public Integer getEnergy() {
		if(Debug.isDebug)
			return getOwner().getEnergy();
		return energy;
	}

	public void setEnergy(Integer energy) {
		this.energy = energy;
	}
	
	@Override
	public void addToEnergy(Integer energy) {
		// TODO Auto-generated method stub
		if(!Integer.valueOf(0).equals(energy)){
			this.energy += energy;
			this.energy = this.energy < 1 ? 1 : this.energy;
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", getOwner().getPlayer());
			map.put("container", getOwner().getContainer());
			map.put("card", getOwner());
			map.put("change", energy);
			map.put("position", getOwner().getContainerPosition());
			NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_State_Energy,map);
			super.notifyObservers(info);
		}
	}
	
	@Override
	public Integer getFlee() {
		// TODO Auto-generated method stub
		return flee;
	}
	
	@Override
	public void setFlee(Integer flee) {
		// TODO Auto-generated method stub
		this.flee = flee;
	}
	
	@Override
	public void addToFlee(Integer flee) {
		// TODO Auto-generated method stub
		if(!Integer.valueOf(0).equals(flee)){
			this.flee += flee;
			this.flee = this.flee < 0 ? 0 : this.flee;
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", getOwner().getPlayer());
			map.put("container", getOwner().getContainer());
			map.put("card", getOwner());
			map.put("change", flee);
			map.put("position", getOwner().getContainerPosition());
			NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_State_Energy,map);
			super.notifyObservers(info);
		}
	}
	
	public Boolean getHide() {
		return hide;
	}

	/**
	 * 隐式的改变隐身状态
	 * @param hide
	 */
	public void setHide(Boolean hide) {
		this.hide = hide;
	}
	
	/**
	 * 显式的改变隐身状态，只能在战场上调用
	 * @param hide
	 */
	public void changeHide(Boolean hide){
		if(!this.hide.equals(hide)){
			this.hide = hide;
			
			/*
			 * 隐身状态的改变在战场上才有意义
			 */
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", getOwner().getPlayer());
			map.put("container", getOwner().getContainer());
			map.put("card", getOwner());
			map.put("position", getOwner().getContainerPosition());
			map.put("hide", hide);
			NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_State_Hide,map);
			notifyObservers(info);
		}
	}

	public Boolean getMoveable() {
		if(Debug.isDebug)
			return Debug.moveable;
		return moveable;
	}
	
	public void setMoveable(Boolean moveable) {
		this.moveable = moveable;
	}
	
	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return (LifeCard) super.getOwner();
	}

	@Override
	public void action(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		IPlace place = (IPlace) objects[0];
		
		IGround ground = (IGround) getOwner().getContainer();
		List<Integer> route = ground.move(getOwner(), place.getPosition(), type);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getPlayer());
		map.put("container", getOwner().getContainer());
		map.put("card", getOwner());
		map.put("route", route);
		map.put("position", place.getPosition());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Action_Move,map);
		super.notifyObservers(info);
	}
}
