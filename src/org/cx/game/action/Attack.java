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
import org.cx.game.widget.IWeapon;

public class Attack extends Action implements IAttack {
	
	private Integer mode = IAttack.Mode_Far;          //模式，近战/远程
	private Integer range = 1;                        //距离
	private Integer lockChance = 0;                   //锁定几率
	private Integer atk = 0;                          //攻击力
	private Boolean counterAttack = false;            //是否是反击
	private Boolean attackable = false;
	
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

	

	public Integer getAtk() {
		if(null!=getWeapon())
			return atk+this.weapon.getAtk();
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
	public Boolean getAttackable() {
		// TODO Auto-generated method stub
		return this.attackable;
	}
	
	@Override
	public void setAttackable(Boolean attackable) {
		// TODO Auto-generated method stub
		this.attackable = attackable;
	}
	
	/**
	 * 武器
	 */
	private IWeapon weapon = null;
	
	@Override
	public IWeapon getWeapon() {
		// TODO Auto-generated method stub
		return this.weapon;
	}
	
	@Override
	public void handWeapon(IWeapon weapon) {
		// TODO Auto-generated method stub
		this.weapon = weapon;
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getPlayer());
		map.put("container", getOwner().getContainer());
		map.put("card", getOwner());
		map.put("position", getOwner().getContainerPosition());
		map.put("weapon", weapon);
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Weapon_Hand,map);
		super.notifyObservers(info);
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
		
		setAttackable(false);
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
