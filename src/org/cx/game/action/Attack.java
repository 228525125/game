package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.exception.CommandValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.JsonOut;
import org.cx.game.tools.Debug;
import org.cx.game.validator.AttackRangeValidator;
import org.cx.game.widget.IControlQueue;

public class Attack extends Action implements IAttack {
	
	private Integer mode = IAttack.Mode_Far;
	private Integer range = 1;
	private Integer type = IAttack.Type_Usually;
	private Integer accurateChance = 0;
	private Integer thumpChance = 0;
	private Integer speedChance = 0;
	private Integer atk = 0;
	
	public Attack(Integer mode, Integer type) {
		// TODO Auto-generated constructor stub
		super();
		this.mode = mode;
		this.type = type;
		setParameterTypeValidator(new Class[]{LifeCard.class});
	}
	
	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return (LifeCard) super.getOwner();
	}
	
	public Integer getRange() {
		return range;
	}

	public void setRange(Integer range) {
		this.range = range;
	}
	
	public Integer getMode() {
		return mode;
	}
	
	public void setMode(Integer type) {
		this.mode = type;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer atkType) {
		this.type = atkType;
	}

	public Integer getAccurateChance() {
		return accurateChance;
	}

	public void setAccurateChance(Integer accurateChance) {
		this.accurateChance = accurateChance;
	}

	public Integer getThumpChance() {
		return thumpChance;
	}

	public void setThumpChance(Integer thumpChance) {
		this.thumpChance = thumpChance;
	}
	
	@Override
	public Integer getSpeedChance() {
		// TODO Auto-generated method stub
		return this.speedChance;
	}
	
	@Override
	public void setSpeedChance(Integer speedChance) {
		// TODO Auto-generated method stub
		if(this.speedChance!=speedChance){
			this.speedChance = speedChance;
			
			/*
			 * 初始化时，owner为null
			 */
			if(null!=getOwner()){
				IControlQueue queue = getOwner().getPlayer().getContext().getQueue();
				queue.refurbish();        //刷新控制列表
			}
		}
	}
	
	@Override
	public void addToSpeedChance(Integer speedChance) {
		// TODO Auto-generated method stub
		this.speedChance += speedChance;
		
		/*
		 * 初始化时，owner为null
		 */
		if(null!=getOwner()){
			IControlQueue queue = getOwner().getPlayer().getContext().getQueue();
			queue.refurbish();        //刷新控制列表
		}
		
	}

	public Integer getAtk() {
		return atk;
	}

	public void setAtk(Integer atk) {
		this.atk = atk;
	}
	
	@Override
	public void addToAtk(Integer atk) {
		// TODO Auto-generated method stub
		this.atk += atk;
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getPlayer());
		map.put("container", getOwner().getContainer());
		map.put("card", getOwner());
		map.put("change", atk);
		map.put("position", getOwner().getContainerPosition());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_State_Atk,map);
		super.notifyObservers(info);
	}
	
	@Override
	public void action(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub		
		super.action(objects);
		
		LifeCard attacked = (LifeCard) objects[0];
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getPlayer());
		map.put("container", getOwner().getContainer());
		map.put("card", getOwner());
		map.put("position", getOwner().getContainerPosition());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Action_Attack,map);
		super.notifyObservers(info);  
		
		attacked.attacked(getOwner());         //比赛规则在attacked中实现的
		
		if(!Debug.isDebug)
			getOwner().getPlayer().getContext().done();
	}
}
