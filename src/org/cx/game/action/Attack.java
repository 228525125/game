package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.card.LifeCard;
import org.cx.game.card.buff.AttackLockBuff;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.rule.AttackRule;
import org.cx.game.rule.RuleGroup;
import org.cx.game.rule.RuleGroupFactory;
import org.cx.game.widget.GroundFactory;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IWeapon;

public class Attack extends Action implements IAttack {
	
	private Integer mode = IAttack.Mode_Far;          //模式，近战/远程
	private Integer range = 1;                        //距离
	private Integer lockChance = 0;                   //锁定几率
	private Integer atk = 0;                          //真实攻击力
	private Integer extraAtk = 0;                     //额外攻击力
	private Integer landformAtk = 0;                  //地形攻击力
	private Boolean counterAttack = false;            //是否是反击
	private Boolean attackable = false;
	
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
			
			/*Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", getOwner().getPlayer());
			map.put("container", getOwner().getContainer());
			map.put("card", getOwner());
			map.put("change", range);
			map.put("position", getOwner().getContainerPosition());
			NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_State_Range,map);
			super.notifyObservers(info);*/
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
			
			/*Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", getOwner().getPlayer());
			map.put("container", getOwner().getContainer());
			map.put("card", getOwner());
			map.put("change", range);
			map.put("position", getOwner().getContainerPosition());
			NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_State_Mode,map);
			super.notifyObservers(info);*/
		}
	}

	public Integer getAtk() {
		return this.atk;
	}
	
	@Override
	public void setAtk(Integer atk) {
		// TODO Auto-generated method stub
		this.atk = atk;
	}
	
	@Override
	public void updateAtk() {
		// TODO Auto-generated method stub
		Integer level = getOwner().getUpgrade().getLevel();
		Double riseRatio = level>1 ? Math.pow(IUpgrade.DefaultLifeCardRiseRatio, level) * 100 : 100d;
		Integer atk = getOwner().getAtk() * riseRatio.intValue() / 100;
		Integer weaponAtk = null!=getWeapon() ? this.weapon.getAtk() : 0;
		Integer landformAtk = getLandformAtk();
		Integer extraAtk = getExtraAtk();
		this.atk = atk + weaponAtk + landformAtk + extraAtk;
	}
	
	public Integer getExtraAtk() {
		// TODO Auto-generated method stub
		return this.extraAtk;
	}

	public void setExtraAtk(Integer extraAtk) {
		this.extraAtk = extraAtk;
		
		updateAtk();
	}
	
	@Override
	public void addToExtraAtk(Integer atk) {
		// TODO Auto-generated method stub
		if(!Integer.valueOf(0).equals(atk)){
			this.extraAtk += atk;
			
			updateAtk();
		}
	}
	
	public Integer getLandformAtk() {
		return landformAtk;
	}

	public void setLandformAtk(Integer landformAtk) {
		this.landformAtk = landformAtk;
		
		updateAtk();
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
			
			/*Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", getOwner().getPlayer());
			map.put("container", getOwner().getContainer());
			map.put("card", getOwner());
			map.put("change", lockChance);
			map.put("position", getOwner().getContainerPosition());
			NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_State_Lock,map);
			super.notifyObservers(info);*/
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
		
		//判断移动攻击
		if(!getOwner().getMobile() && getOwner().getMove().getMoveable()){
			getOwner().getMove().setMoveable(false);
		}
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
		
		updateAtk();
		
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
		
		IAttack clone = clone();
		
		IGround ground = GroundFactory.getGround();
		Integer distance = ground.distance(attacked.getContainerPosition(), getOwner().getContainerPosition());
		if(IDeath.Status_Live == attacked.getDeath().getStatus()
		&& 1==distance){                                           //近身
			new AttackLockBuff(getOwner(),attacked).effect();
			
			clone.setMode(IAttack.Mode_Near);             //如果是远程，这里要设置为近身攻击模式
		}
		
		//判断是否装备武器
		if(null!=getWeapon()){
			getWeapon().addToWear(-1);
		}
		
		//判断攻击模式，远程近战攻击减半
		if(IAttack.Mode_Far.equals(getMode()) && 1==distance){
			clone.setMode(IAttack.Mode_Near);
			Integer atk = clone.getAtk()/2;
			clone.addToExtraAtk(-atk);
		}
		
		//生命值与攻击力成正比
		IDeath death = getOwner().getDeath();
		Integer scale = death.getHp()*100/death.getHpLimit();
		if(scale<100){
			Integer atk = clone.getAtk()*(100-scale)/100;
			clone.addToExtraAtk(-atk);
		}
		
		attacked.attacked(getOwner(), clone);
		
		setAttackable(false);                   //反击同样适用
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
