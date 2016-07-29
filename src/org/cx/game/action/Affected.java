package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.card.magic.IMagic;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;

/**
 * 受到法术影响
 * @author chenxian
 *
 */
public class Affected extends Action implements IAffected {

	@Override
	public void action(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		super.action(objects);
		
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

}
