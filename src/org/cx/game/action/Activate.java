package org.cx.game.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cx.game.card.LifeCard;
import org.cx.game.card.buff.AttackLockBuff;
import org.cx.game.card.buff.IBuff;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.tools.Debug;

public class Activate extends Action implements IActivate {

	private Boolean activation = false;
	
	@Override
	public Boolean getActivation() {
		// TODO Auto-generated method stub
		if(Debug.isDebug)
			return Debug.activate;
		return this.activation;
	}

	@Override
	public void setActivation(Boolean activation) {
		// TODO Auto-generated method stub
		this.activation = activation;
	}
	
	@Override
	public void action(Object... objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		super.action(objects);
		
		Boolean activate = (Boolean) objects[0];
		
		setActivation(activate);
		
		LifeCard owner = (LifeCard) getOwner();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", owner.getPlayer());
		map.put("container", owner.getContainer());
		map.put("card", owner);
		map.put("position", owner.getContainerPosition());
		map.put("activate", activate);
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Action_Activate,map);
		notifyObservers(info);
		
		if(activation){
			owner.getMove().setMoveable(true);
			owner.getAttacked().setAttackBack(true);
			List<IBuff> buffs = owner.getNexusBuff(AttackLockBuff.class);  //清除锁定对象
			for(IBuff buff : buffs)
				owner.removeNexusBuff(buff);
		}else{
			owner.getMove().setMoveable(false);
		}
	}

}
