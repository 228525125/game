package org.cx.game.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
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
	
	private List<Integer> path = new ArrayList<Integer>();
	
	private Integer direction = IGround.Relative_Right;
	
	@Override
	public Integer getType() {
		// TODO Auto-generated method stub
		return this.type;
	}
	
	@Override
	public void setType(Integer type) {
		// TODO Auto-generated method stub
		if(!this.type.equals(type))
			this.type = type;
	}
	
	public Integer getConsume() {
		return consume;
	}

	public Integer getEnergy() {
		return energy;
	}

	public void setEnergy(Integer energy) {
		if(!this.energy.equals(energy)){
			energy = 0>energy ? 0 : energy;
			this.energy = energy;
			
			if(Integer.valueOf(0).equals(this.energy))
				setMoveable(false);
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
		if(!this.flee.equals(flee))
			this.flee = flee;
	}
	
	public Boolean getHide() {
		return hide;
	}

	/**
	 * 隐式的改变隐身状态
	 * @param hide
	 */
	public void setHide(Boolean hide) {
		if(!this.hide.equals(hide))
			this.hide = hide;
	}

	public Boolean getMoveable() {
		return moveable;
	}
	
	public void setMoveable(Boolean moveable) {
		if(!this.moveable.equals(moveable))
			this.moveable = moveable;
	}
	
	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		if(!direction.equals(this.direction))
			this.direction = direction;
	}
	
	public List<Integer> getMovePath() {
		return path;
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
		
		/*
		 * 一个回合只能移动一次，移动攻击类型除外，例如骑兵
		 */
		if(!getOwner().getMobile())
			setMoveable(false);     
	}
}
