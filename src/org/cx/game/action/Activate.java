package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.rule.ActivateRule;
import org.cx.game.tools.Debug;

public class Activate extends Action implements IActivate {

	private Boolean activation = false;
	
	public Activate() {
		// TODO Auto-generated constructor stub
		addObserver(new ActivateRule(this));
	}
	
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
		
		Boolean activate = (Boolean) objects[0];
		
		setActivation(activate);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getPlayer());
		map.put("container", getOwner().getContainer());
		map.put("card", getOwner());
		map.put("position", getOwner().getContainerPosition());
		map.put("activate", activate);
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Action_Activate,map);
		notifyObservers(info);
		
		
	}
	
	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return (LifeCard) super.getOwner();
	}

}
