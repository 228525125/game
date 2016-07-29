package org.cx.game.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.card.buff.AttackLockBuff;
import org.cx.game.card.buff.IBuff;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.exception.CommandValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.JsonOut;
import org.cx.game.tools.Debug;
import org.cx.game.validator.AttackRangeValidator;
import org.cx.game.widget.IControlQueue;
import org.cx.game.widget.IGround;

public class Attack extends Action implements IAttack {
	
	private Integer mode = IAttack.Mode_Far;
	private Integer range = 1;
	private Integer speedChance = 0;
	private Integer lockChance = 0;
	private Integer atk = 0;
	
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
	
	public Integer getMode() {
		return mode;
	}
	
	public void setMode(Integer mode) {
		this.mode = mode;
	}
	
	public void changeMode(Integer mode){
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
		this.speedChance += speedChance;
		this.speedChance = this.speedChance < 0 ? 0 : this.speedChance;		
	}

	public Integer getAtk() {
		return atk;
	}

	public void setAtk(Integer atk) {
		this.atk = atk;
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
	public void addToAtk(Integer atk) {
		// TODO Auto-generated method stub
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
		
		IGround ground = getOwner().getPlayer().getGround();
		Integer distance = ground.easyDistance(attacked.getContainerPosition(), getOwner().getContainerPosition());
		if(IDeath.Status_Live == attacked.getDeath().getStatus()
		&& 1==distance){                                           //近身
			
			List<IBuff> buffList = getOwner().getNexusBuff(AttackLockBuff.class);
			for(IBuff buff : buffList)
				buff.invalid();
			
			new AttackLockBuff(2,getOwner(),attacked).effect();
		}
		
		IAttack attack = getOwner().getAttack().clone();
		if(IAttack.Mode_Far.equals(getMode()) && 1==distance){
			attack.setMode(IAttack.Mode_Near);
			Integer atk = attack.getAtk()/2;
			attack.setAtk(atk);
		}
		
		attacked.attacked(getOwner(), attack);
		
		if(!Debug.isDebug)
			getOwner().getPlayer().getContext().done();
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
