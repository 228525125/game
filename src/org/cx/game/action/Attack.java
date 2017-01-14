package org.cx.game.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.card.buff.IBuff;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.exception.CommandValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.JsonOut;
import org.cx.game.rule.AttackRule;
import org.cx.game.rule.IRule;
import org.cx.game.tools.Debug;
import org.cx.game.validator.AttackRangeValidator;
import org.cx.game.widget.IControlQueue;
import org.cx.game.widget.IGround;

public class Attack extends Action implements IAttack {
	
	private Integer mode = IAttack.Mode_Far;          //模式，近战/远程
	private Integer range = 1;                        //距离
	private Integer speedChance = 0;                  //速度
	private Integer lockChance = 0;                   //锁定几率
	private Integer atk = 0;                          //攻击力
	private Boolean counterAttack = false;            //是否是反击
	
	private AttackRule rule = new AttackRule(this); 
	
	public Attack() {
		// TODO Auto-generated constructor stub
		addObserver(rule);
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
	
	@Override
	public void addToRange(Integer range) {
		// TODO Auto-generated method stub
		if(!Integer.valueOf(0).equals(range)){
			this.range += range;
			this.range = this.range < 1 ? 1 : this.range;
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", getOwner().getPlayer());
			map.put("container", getOwner().getContainer());
			map.put("card", getOwner());
			map.put("change", range);
			map.put("position", getOwner().getContainerPosition());
			NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_State_Range,map);
			super.notifyObservers(info);
		}
	}
	
	public Integer getMode() {
		return mode;
	}
	
	public void setMode(Integer mode) {
		this.mode = mode;
	}
	
	public void changeMode(Integer mode){
		if(!this.mode.equals(mode)){
			this.mode = mode;
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", getOwner().getPlayer());
			map.put("container", getOwner().getContainer());
			map.put("card", getOwner());
			map.put("change", range);
			map.put("position", getOwner().getContainerPosition());
			NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_State_Mode,map);
			super.notifyObservers(info);
		}
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
		}
	}
	
	@Override
	public void addToSpeedChance(Integer speedChance) {
		// TODO Auto-generated method stub
		if(!Integer.valueOf(0).equals(speedChance)){
			this.speedChance += speedChance;
			this.speedChance = this.speedChance < 0 ? 0 : this.speedChance;		
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", getOwner().getPlayer());
			map.put("container", getOwner().getContainer());
			map.put("card", getOwner());
			map.put("change", speedChance);
			map.put("position", getOwner().getContainerPosition());
			NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_State_Speed,map);
			super.notifyObservers(info);
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
		if(!Integer.valueOf(0).equals(atk)){
			this.atk += atk;
			this.atk = this.atk < 0 ? 0 : this.atk;
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", getOwner().getPlayer());
			map.put("container", getOwner().getContainer());
			map.put("card", getOwner());
			map.put("change", atk);
			map.put("position", getOwner().getContainerPosition());
			NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_State_Atk,map);
			super.notifyObservers(info);
		}
	}
	
	@Override
	public Integer getLockChance() {
		// TODO Auto-generated method stub
		return lockChance;
	}

	@Override
	public void setLockChance(Integer lockChance) {
		// TODO Auto-generated method stub
		this.lockChance = lockChance;
	}
	
	@Override
	public void addToLockChance(Integer lockChance) {
		// TODO Auto-generated method stub
		if(!Integer.valueOf(0).equals(lockChance)){
			this.lockChance += lockChance;
			this.lockChance = this.lockChance < 0 ? 0 : this.lockChance;
			this.lockChance = this.lockChance > 100 ? 100 : this.lockChance;
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", getOwner().getPlayer());
			map.put("container", getOwner().getContainer());
			map.put("card", getOwner());
			map.put("change", lockChance);
			map.put("position", getOwner().getContainerPosition());
			NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_State_Lock,map);
			super.notifyObservers(info);
		}
	}
	
	public Boolean getCounterAttack() {
		return counterAttack;
	}

	public void setCounterAttack(Boolean counterAttack) {
		this.counterAttack = counterAttack;
	}

	@Override
	public void action(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub		
		LifeCard attacked = (LifeCard) objects[0];
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getPlayer());
		map.put("container", getOwner().getContainer());
		map.put("attack", getOwner());
		map.put("attacked", attacked);
		map.put("position", getOwner().getContainerPosition());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Action_Attack,map);
		super.notifyObservers(info);
		
		attacked.attacked(getOwner(), this.rule.cloneForAttack());
	}
	
	public IAttack clone() {
		// TODO Auto-generated method stub
		try {
			return (IAttack) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
