package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.IActiveSkill;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.rule.ConjureRule;
import org.cx.game.rule.IRule;

/**
 * 施法一个主动技能
 * @author chenxian
 *
 */
public class Conjure extends Action implements IConjure {
	
	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return (LifeCard) super.getOwner();
	}
	
	@Override
	public ConjureDecorator getDecorator() {
		// TODO Auto-generated method stub
		return (ConjureDecorator) super.getDecorator();
	}

	@Override
	public void action(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		IActiveSkill skill = (IActiveSkill) objects[0];
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getPlayer());
		map.put("container", getOwner().getContainer());
		map.put("card", getOwner());
		map.put("position", getOwner().getContainerPosition());
		map.put("skill", skill);
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Action_Conjure,map);
		super.notifyObservers(info);	
		
		Object [] parameter = (Object[]) objects[1];
		skill.useSkill(parameter);
	}
}
