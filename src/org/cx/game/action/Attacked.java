package org.cx.game.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cx.game.card.LifeCard;
import org.cx.game.card.buff.IBuff;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.rule.AttackRule;
import org.cx.game.rule.AttackedRule;
import org.cx.game.rule.IRule;

/**
 * 受到攻击
 * @author chenxian
 *
 */
public class Attacked extends Action implements IAttacked {

	private Boolean fightBack = false;
	private Integer armour = 0;
	private Integer def = 0;
	
	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return (LifeCard) super.getOwner();
	}
	
	@Override
	public Integer getDef() {
		// TODO Auto-generated method stub
		return this.def;
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
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", getOwner().getPlayer());
			map.put("container", getOwner().getContainer());
			map.put("card", getOwner());
			map.put("change", this.def);
			map.put("position", getOwner().getContainerPosition());
			NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_State_Def,map);
			super.notifyObservers(info);
		}
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
	
	@Override
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
	}

	@Override
	public void action(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		IAttack attack = (IAttack) objects[1];
		Integer damage = -attack.getAtk();		
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getPlayer());
		map.put("container", getOwner().getContainer());
		map.put("attack", attack.getOwner());
		map.put("attacked", getOwner());
		map.put("position", getOwner().getContainerPosition());
		map.put("damage", damage);
		map.put("ruleParam", attack);
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Action_Attacked,map);
		super.notifyObservers(info);
	}
}
