package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.JsonOut;
import org.cx.game.tools.Debug;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IPlace;

public class Move extends Action implements IMove{

	private Integer energy = 0;
	private Integer consume = IMove.Consume;        //移动一格的消耗
	private Integer type = Type_Walk;
	private Boolean moveable = false;   //是否能移动，回合内只能移动一次
	private Integer fleeChance = 0;         //逃离成功率
	
	public Move(Integer type) {
		// TODO Auto-generated constructor stub
		super();
		this.type = type;
		setParameterTypeValidator(new Class[]{IPlace.class});
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
	
	public Integer getConsume() {
		return consume;
	}

	public void setConsume(Integer consume) {
		this.consume = consume;
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
	public Integer getFleeChance() {
		// TODO Auto-generated method stub
		return fleeChance;
	}
	
	@Override
	public void setFleeChance(Integer fleeChance) {
		// TODO Auto-generated method stub
		this.fleeChance = fleeChance;
	}
	
	@Override
	public void addToEnergy(Integer energy) {
		// TODO Auto-generated method stub
		this.energy += energy;
	}
	
	public Boolean getMoveable() {
		if(Debug.isDebug)
			return Debug.moveable;
		return moveable;
	}

	public void setMoveable(Boolean moveable) {
		this.moveable = moveable;
		
		if(!moveable){
			energy = 0;
		}
	}
	
	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return (LifeCard) super.getOwner();
	}

	@Override
	public void action(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		super.action(objects);
		
		IPlace place = (IPlace) objects[0];
		Integer begin = getOwner().getContainerPosition();
		IGround ground = (IGround) getOwner().getContainer();
		ground.move(getOwner(), place.getPosition(), type);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getPlayer());
		map.put("container", getOwner().getContainer());
		map.put("card", getOwner());
		map.put("begin", begin);
		map.put("end", place.getPosition());
		map.put("position", place.getPosition());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Action_Move,map);
		super.notifyObservers(info);
		
		setMoveable(false);     //一个回合只能移动一次
	}
	
}
