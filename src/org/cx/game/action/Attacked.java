package org.cx.game.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cx.game.card.LifeCard;
import org.cx.game.card.buff.AttackLockBuff;
import org.cx.game.card.buff.IBuff;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.rule.IRule;

public class Attacked extends Action implements IAttacked {

	private Boolean fightBack = false;
	
	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return (LifeCard) super.getOwner();
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
	public void action(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		super.action(objects);
		
		LifeCard life = (LifeCard) objects[0];
		IAttack attack = (IAttack) objects[1];
		Integer damage1 = attack.getAtk();
		
		IDeath death = getOwner().getDeath();
		death.attackToDamage(-damage1);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getPlayer());
		map.put("container", getOwner().getContainer());
		map.put("attack", attack.getOwner());
		map.put("attacked", getOwner());
		map.put("position", getOwner().getContainerPosition());
		map.put("damage", -damage1);
		map.put("ruleParam", attack);
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Action_Attacked,map);
		super.notifyObservers(info);		
		
		/*
		 * 反击
		 */
		Integer mode1 = attack.getMode();
		
		Integer mode2 = getOwner().getAttack().getMode();
		Integer damage2 = getOwner().getAttack().getAtk();
		if(IAttack.Mode_Near.equals(mode1) && IAttack.Mode_Far.equals(mode2)){
			Integer atk = getOwner().getAttack().getAtk();
			atk /= 2;
			damage2 = atk;
		}
		
		if(IAttack.Mode_Near.equals(attack.getMode()) && IDeath.Status_Live.equals(death.getStatus()) && getAttackBack()){
			life.getDeath().attackToDamage(-damage2);
			setAttackBack(false);
			
			List<IBuff> buffList = getOwner().getNexusBuff(AttackLockBuff.class);
			for(IBuff buff : buffList)
				buff.invalid();
			
			new AttackLockBuff(2,getOwner(),life).effect();
			
			Map<String,Object> m = new HashMap<String,Object>();
			m.put("player", life.getPlayer());
			m.put("container", life.getContainer());
			m.put("card", life);
			m.put("position", life.getContainerPosition());
			m.put("damage", -damage2);
			NotifyInfo in = new NotifyInfo(NotifyInfo.Card_LifeCard_Action_Attacked,m);
			super.notifyObservers(in);
		}

	}

	@Override
	public IRule getRule() {
		// TODO Auto-generated method stub
		return null;
	}
}
