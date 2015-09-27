package org.cx.game.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.Intercepter;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.JsonOut;
import org.cx.game.tools.Util;
import org.cx.game.widget.IGround;

public class Attacked extends Action implements IAttacked {
	
	private Integer attackBackChance = 0;
	private Integer dodgeChance = 0;
	private Integer parryChance = 0;
	private Integer immuneDamageRatio = 0;	
	
	public Attacked(Integer armourType) {
		// TODO Auto-generated constructor stub
		super();
		this.armourType = armourType;
		setParameterTypeValidator(new Class[]{LifeCard.class});
	}
	
	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return (LifeCard) super.getOwner();
	}
	
	/**
	 * 防御类型
	 */
	private Integer armourType = IAttacked.Type_Non;

	public Integer getArmourType() {
		return armourType;
	}
	
	public void setArmourType(Integer armourType) {
		this.armourType = armourType;
	}

	public Integer getAttackBackChance() {
		return attackBackChance;
	}

	public void setAttackBackChance(Integer attackBackChance) {
		this.attackBackChance = attackBackChance;
	}

	public Integer getDodgeChance() {
		return dodgeChance;
	}

	public void setDodgeChance(Integer dodgeChance) {
		this.dodgeChance = dodgeChance;
	}
	
	@Override
	public void addToDodgeChance(Integer dodgeChance) {
		// TODO Auto-generated method stub
		this.dodgeChance += dodgeChance;
	}

	public Integer getParryChance() {
		return parryChance;
	}

	public void setParryChance(Integer parryChance) {
		this.parryChance = parryChance;
	}

	public Integer getImmuneDamageRatio() {
		return immuneDamageRatio;
	}
	
	public void addToImmuneDamageRatio(Integer damageChance){
		this.immuneDamageRatio = immuneDamageRatio + damageChance;
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getPlayer());
		map.put("container", getOwner().getContainer());
		map.put("card", getOwner());
		map.put("change", damageChance);
		map.put("position", getOwner().getContainerPosition());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_State_ImmuneDamageRatio,map);
		super.notifyObservers(info); 
	}

	public void setImmuneDamageRatio(Integer damageChance) {
		this.immuneDamageRatio = damageChance;
	}

	@Override
	public void action(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		super.action(objects);
		
		LifeCard attack = (LifeCard) objects[0];
		Integer atk = attack.getAttack().getAtk();
		Integer damageChance = 100-getOwner().getAttacked().getImmuneDamageRatio();
		
		Integer damage = atk*damageChance;
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getPlayer());
		map.put("container", getOwner().getContainer());
		map.put("card", getOwner());
		map.put("position", getOwner().getContainerPosition());
		map.put("damage", damage);
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Action_Attacked,map);
		super.notifyObservers(info);
		
		getOwner().getDeath().attackToDamage(-damage.intValue());
		
		
	}

}
