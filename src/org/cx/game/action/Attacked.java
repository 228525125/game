package org.cx.game.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cx.game.action.Death.DeathAddToHpAction;
import org.cx.game.card.HeroCard;
import org.cx.game.card.LifeCard;
import org.cx.game.card.buff.IBuff;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.rule.AttackRule;
import org.cx.game.rule.AttackedRule;
import org.cx.game.rule.IRule;
import org.cx.game.widget.treasure.ITreasure;
import org.cx.game.widget.treasure.TreasureEquipment;

/**
 * 受到攻击
 * @author chenxian
 *
 */
public class Attacked extends Action implements IAttacked {

	private Boolean fightBack = false;
	private Integer armour = 0;
	private Integer def = 0;         //真实防御力
	private Integer armourDef = 0;   //装备防御力
	private Integer extraDef = 0;    //额外防御力
	private Integer landformDef = 0; //地形防御力
	
	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return (LifeCard) super.getOwner();
	}
	
	@Override
	public Integer getDef() {
		// TODO Auto-generated method stub
		return this.def+getLandformDef();
	}
	
	@Override
	public void setDef(Integer def) {
		// TODO Auto-generated method stub
		this.def = def;
	}
	
	@Override
	public void addToDef(Integer def) {
		// TODO Auto-generated method stub
		if(0<def){
			this.def += def;
			this.def = this.def<0 ? 0 : this.def;
			
			/*Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", getOwner().getPlayer());
			map.put("container", getOwner().getContainer());
			map.put("card", getOwner());
			map.put("change", this.def);
			map.put("position", getOwner().getContainerPosition());
			NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_State_Def,map);
			super.notifyObservers(info);*/
		}
	}
	
	@Override
	public Integer getArmourDef() {
		// TODO Auto-generated method stub
		return this.armourDef;
	}
	
	@Override
	public void updateArmourDef() {
		// TODO Auto-generated method stub
		if (getOwner() instanceof HeroCard) {
			HeroCard hero = (HeroCard) getOwner();
			Integer def = 0;
			for(ITreasure treasure : hero.getTreasures()){
				if (treasure instanceof TreasureEquipment) {
					TreasureEquipment te = (TreasureEquipment) treasure;
					def += te.getDef();
				}
			}
			this.armourDef = def;
		}
	}
	
	public Integer getLandformDef() {
		return landformDef;
	}

	public void setLandformDef(Integer landformDef) {
		this.landformDef = landformDef;
	}

	public Integer getExtraDef() {
		return extraDef;
	}

	public void setExtraDef(Integer extraDef) {
		this.extraDef = extraDef;
	}
	
	public void updateDef(){
		Integer level = getOwner().getUpgrade().getLevel();
		Double riseRatio = level>1 ? Math.pow(IUpgrade.DefaultLifeCardRiseRatio, level) * 100 : 100d;
		Integer def = getOwner().getDef() * riseRatio.intValue() / 100;
		Integer armourDef = getArmourDef();
		Integer landformDef = getLandformDef();
		Integer extraDef = getExtraDef();
		this.def = def + armourDef + landformDef + extraDef;
	}

	@Override
	public Boolean getFightBack() {
		// TODO Auto-generated method stub
		return this.fightBack;
	}
	
	@Override
	public void setFightBack(Boolean fightBack) {
		// TODO Auto-generated method stub
		this.fightBack = fightBack;
	}
	
	@Override
	public Integer getArmour() {
		// TODO Auto-generated method stub
		return this.armour;
	}
	
	@Override
	public void setArmour(Integer armour) {
		// TODO Auto-generated method stub
		this.armour = armour;
	}
	
	/*
	public Integer addToArmour(Integer armour) {
		// TODO Auto-generated method stub
		Integer ret = 0;
		if(!Integer.valueOf(0).equals(armour)){
			this.armour += armour;
			ret = this.armour<0 ? this.armour : 0;
			this.armour = this.armour>0 ? this.armour : 0;
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", getOwner().getPlayer());
			map.put("container", getOwner().getContainer());
			map.put("card", getOwner());
			map.put("change", armour);
			map.put("position", getOwner().getContainerPosition());
			NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_State_Armour,map);
			super.notifyObservers(info);
		}
		return ret;
	}*/

	@Override
	public void action(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		
		IAttack attack = (IAttack) objects[1];
		
		/*
		 * 伤害 使用H3计算规则
		 */
		Integer atk = attack.getAtk();
		Integer def = getDef();
		Integer temp = atk - def;
		Integer ratio = temp<0 ? temp*25 : temp*50;
		ratio += 1000;
		Integer[] dmg = attack.getDmg();
		Integer result = Random.nextInt(dmg[1]-dmg[0]);
		Integer damage =  dmg[0]+result;
		damage = damage*ratio/1000;
		damage = -damage;
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getPlayer());
		map.put("container", getOwner().getContainer());
		map.put("attack", attack.getOwner());
		map.put("attacked", getOwner());
		map.put("position", getOwner().getPosition());
		map.put("damage", damage);
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Action_Attacked,map);
		super.notifyObservers(info);
		
		//造成的实际伤害
		IDeath death = getOwner().getDeath();
		death.addToHp(damage);
		damage = ((DeathAddToHpAction) death.getAddToHpAction()).getDamage();
		
		//增加经验值
		IUpgradeLife lu = (IUpgradeLife) attack.getOwner().getUpgrade();
		lu.addToEmpiricValue(Math.abs(damage));
	}
}
