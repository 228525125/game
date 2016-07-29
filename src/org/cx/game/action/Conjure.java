package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.IActiveSkill;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.tools.Debug;
import org.cx.game.validator.ConjurePowerValidator;

/**
 * 施法
 * @author chenxian
 *
 */
public class Conjure extends Action implements IConjure {

	private Integer power = 0;

	public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}
	
	@Override
	public ConjureDecorator getDecorator() {
		// TODO Auto-generated method stub
		return (ConjureDecorator) super.getDecorator();
	}

	@Override
	public void action(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub		
		super.action(objects);
		
		IActiveSkill skill = (IActiveSkill) objects[0];
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getPlayer());
		map.put("container", getOwner().getContainer());
		map.put("card", getOwner());
		map.put("position", getOwner().getContainerPosition());
		map.put("skill", skill);
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Action_Conjure,map);
		super.notifyObservers(info);
		
		getDecorator().setPower(power-skill.getConsume());		
		
		Object [] parameter = (Object[]) objects[1];
		skill.useSkill(parameter);
		
		if(!Debug.isDebug)
			getOwner().getPlayer().getContext().done();
	}

}
