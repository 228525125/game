package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.card.magic.IMagic;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.rule.IRule;

/**
 * 受到法术影响
 * @author chenxian
 *
 */
public class Affected extends Action implements IAffected {
	
	@Override
	public void action(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		IMagic magic = (IMagic) objects[0];
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getPlayer());
		map.put("container", getOwner().getContainer());
		map.put("card", getOwner());
		map.put("position", getOwner().getContainerPosition());
		map.put("magic", magic);
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Action_Affected,map);
		super.notifyObservers(info); 
		
		magic.affect(getOwner());
	}
	
	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return (LifeCard) super.getOwner();
	}
	
	@Override
	public void magicHarm(Integer harm) {
		// TODO Auto-generated method stub
		Integer damage = getOwner().getAttacked().addToArmour(harm);
		getOwner().getDeath().addToHp(damage);
	}

}
