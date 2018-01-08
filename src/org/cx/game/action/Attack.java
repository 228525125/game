package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.card.HeroCard;
import org.cx.game.card.LifeCard;
import org.cx.game.card.buff.AttackLockBuff;
import org.cx.game.card.buff.IBuff;
import org.cx.game.card.skill.IPassiveSkill;
import org.cx.game.card.skill.ISkill;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.widget.GroundFactory;
import org.cx.game.widget.IGround;
import org.cx.game.widget.treasure.ITreasure;
import org.cx.game.widget.treasure.TreasureEquipment;

public class Attack extends Action implements IAttack {
	
	private Integer mode = IAttack.Mode_Far;          //模式，近战/远程
	private Integer range = 1;                        //距离
	private Integer lockChance = 0;                   //锁定几率
	private Integer atk = 0;                          //真实攻击力
	private Integer extraAtk = 0;                     //额外攻击力
	private Integer landformAtk = 0;                  //地形攻击力
	private Integer weaponAtk = 0;                    //武器攻击力
	private Boolean counterAttack = false;            //是否是反击
	private Boolean attackable = false;
	private Boolean mobile = false;                   //是否可移动攻击
	
	public static String space = "8008";              //伤害间隔符
	private Integer  dmg = 180081;                          //伤害 180082 = 1-2
	
	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return (LifeCard) super.getOwner();
	}
	
	public Integer getRange() {
		return range;
	}

	public void setRange(Integer range) {
		if(!range.equals(this.range))
			this.range = range;
	}
	
	public Integer getMode() {
		return mode;
	}
	
	public void setMode(Integer mode) {
		if(!mode.equals(this.mode))
			this.mode = mode;
	}
	
	/**
	 * 移动攻击，例如，骑兵
	 * @return
	 */
	public Boolean getMobile() {
		return mobile;
	}

	public void setMobile(Boolean mobile) {
		this.mobile = mobile;
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
		Integer atk = getOwner().getAtk();
		Integer weaponAtk = getWeaponAtk();
		Integer landformAtk = getLandformAtk();
		Integer extraAtk = getExtraAtk();
		setAtk(atk + weaponAtk + landformAtk + extraAtk);
	}

	public Integer getWeaponAtk() {
		return weaponAtk;
	}
	
	public void setWeaponAtk(Integer weaponAtk) {
		// TODO Auto-generated method stub
		if(!weaponAtk.equals(this.weaponAtk)){
			this.weaponAtk = weaponAtk;
		
			updateAtk();
		}
	}

	public void updateWeaponAtk() {
		if (getOwner() instanceof HeroCard) {
			HeroCard hero = (HeroCard) getOwner();
			Integer atk = 0;
			for(ITreasure treasure : hero.getTreasures()){
				if (treasure instanceof TreasureEquipment) {
					TreasureEquipment te = (TreasureEquipment) treasure;
					atk += te.getAtk();
				}
			}
			setWeaponAtk(atk);
		}
	}

	public Integer getExtraAtk() {
		// TODO Auto-generated method stub
		return this.extraAtk;
	}

	public void setExtraAtk(Integer extraAtk) {
		if(!extraAtk.equals(this.extraAtk)){
			this.extraAtk = extraAtk;
		
			updateAtk();
		}
	}
	
	@Override
	public void updateExtraAtk() {
		// TODO Auto-generated method stub
		Integer levelAtk = getOwner().getUpgrade().getLevel();
		Integer buffAtk = 0;
		for(IBuff buff : getOwner().getBuffList()){
			buffAtk += buff.getAtk();
		}
		Integer skillAtk = 0;
		for(ISkill skill : getOwner().getSkillList()){
			if (skill instanceof IPassiveSkill) {
				IPassiveSkill ps = (IPassiveSkill) skill;
				skillAtk += ps.getAtk();
			}
		}
		setExtraAtk(levelAtk + buffAtk + skillAtk);
	}
	
	public Integer getLandformAtk() {
		return landformAtk;
	}

	public void setLandformAtk(Integer landformAtk) {
		if(!landformAtk.equals(this.landformAtk)){
			this.landformAtk = landformAtk;
			
			updateAtk();
		}
	}

	public Integer getDmg() {
		return dmg;
	}

	public void setDmg(Integer dmg) {
		this.dmg = dmg;
	}

	@Override
	public void updateDmg() {
		// TODO Auto-generated method stub
		Integer [] dmg = IntegerToDamage(getOwner().getDmg());
		ICall call = getOwner().getCall();
		Integer d = DamageToInteger(new Integer[]{dmg[0]*call.getNop(),dmg[1]*call.getNop()});
		setDmg(d);
	}

	@Override
	public Integer getLockChance() {
		// TODO Auto-generated method stub
		return lockChance;
	}

	@Override
	public void setLockChance(Integer lockChance) {
		// TODO Auto-generated method stub
		if(!lockChance.equals(this.lockChance)){
			this.lockChance = lockChance;
		}
	}
	
	public Boolean getCounterAttack() {
		return counterAttack;
	}

	public void setCounterAttack(Boolean counterAttack) {
		if(!counterAttack.equals(this.counterAttack)){
			this.counterAttack = counterAttack;
		}
	}
	
	@Override
	public Boolean getAttackable() {
		// TODO Auto-generated method stub
		return this.attackable;
	}
	
	@Override
	public void setAttackable(Boolean attackable) {
		// TODO Auto-generated method stub
		if(!attackable.equals(this.attackable)){
			this.attackable = attackable;
			
			//判断移动攻击
			if(!getOwner().getMobile() && getOwner().getMove().getMoveable()){
				getOwner().getMove().setMoveable(false);
			}
		}
	}

	@Override
	public void action(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		
		LifeCard attacked = (LifeCard) objects[0];
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("attack", getOwner());
		map.put("attacked", attacked);
		map.put("position", getOwner().getPosition());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Action_Attack,map);
		super.notifyObservers(info);
		
		IAttack clone = clone();
		
		IGround ground = GroundFactory.getGround();
		Integer distance = ground.distance(attacked.getPosition(), getOwner().getPosition());
		if(IDeath.Status_Live == attacked.getDeath().getStatus()
		&& 1==distance){                                           //近身
			new AttackLockBuff(getOwner(),attacked).effect();
			
			clone.setMode(IAttack.Mode_Near);             //如果是远程，这里要设置为近身攻击模式
		}
		
		//判断攻击模式，远程近战伤害减半
		if(IAttack.Mode_Far.equals(getMode()) && 1==distance){
			clone.setMode(IAttack.Mode_Near);
			Integer [] dmg = IntegerToDamage(clone.getDmg());
			clone.setDmg(DamageToInteger(new Integer[]{dmg[0]/2,dmg[1]/2}));
		}
		
		//判断潜行状态
		if(getOwner().getMove().getHide()){
			getOwner().getMove().setHide(false);
		}
		
		/*
		 * 生成朝向信息
		 */
		Integer direction = ground.getDirection(getOwner().getPosition(), attacked.getPosition());
		getOwner().getMove().setDirection(direction);
		
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
	
	public static Integer[] IntegerToDamage(Integer dmg){
		String [] dmgs = dmg.toString().split(space);
		Integer x = Integer.valueOf(dmgs[0]);
		Integer y = Integer.valueOf(dmgs[1]);
		return new Integer [] {x,y};
	}
	
	public static Integer DamageToInteger(Integer [] dmg){
		return Integer.valueOf(dmg[0]+space+dmg[1]);
	}
}
