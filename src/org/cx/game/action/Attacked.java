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

public class Attacked extends Action implements IAttacked {

	private Boolean fightBack = false;
	
	public Attacked() {
		// TODO Auto-generated constructor stub
		addObserver(new AttackedRule(this));
	}
	
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
		
		IAttack attack = (IAttack) objects[1];
		Integer damage1 = attack.getAtk();		
		
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
		 * 先反击，再死亡
		 */
		IDeath death = getOwner().getDeath();
		death.attackToDamage(-damage1);
	}
}
