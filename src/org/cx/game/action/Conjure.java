package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.corps.Corps;
import org.cx.game.magic.skill.IActiveSkill;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;

/**
 * 施法一个主动技能
 * @author chenxian
 *
 */
public class Conjure extends Action implements IConjure {
	
	@Override
	public Corps getOwner() {
		// TODO Auto-generated method stub
		return (Corps) super.getOwner();
	}

	@Override
	public void action(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		
		IActiveSkill skill = (IActiveSkill) objects[0];
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("card", getOwner());
		map.put("position", getOwner().getPosition());
		map.put("skill", skill);
		NotifyInfo info = new NotifyInfo(NotifyInfo.Corps_Conjure,map);
		super.notifyObservers(info);	
		
		Object [] parameter = (Object[]) objects[1];
		skill.useSkill(parameter);
		
		getOwner().getAttack().setAttackable(false);
	}
}
