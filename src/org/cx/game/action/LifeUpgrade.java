package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.rule.IRule;
import org.cx.game.rule.UpgradeRule;

public class LifeUpgrade extends Upgrade implements IUpgrade {
	
	private Integer empiricValue = 0;
	
	private IRule rule = new UpgradeRule(this);
	
	public LifeUpgrade() {
		// TODO Auto-generated constructor stub
		addObserver(rule);
	}
	
	
	public Integer getEmpiricValue() {
		// TODO Auto-generated method stub
		return this.empiricValue;
	}
	
	@Override
	public Integer getProcess() {
		// TODO Auto-generated method stub
		return getEmpiricValue()*100/getConsume();
	}
	
	@Override
	public Integer getWaitBout() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void addToEmpiricValue(Integer empiricValue) {
		// TODO Auto-generated method stub
		if(!Integer.valueOf(0).equals(empiricValue)){
			this.empiricValue += empiricValue;
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", getOwner().getPlayer());
			map.put("container", getOwner().getContainer());
			map.put("position", getOwner().getContainerPosition());
			map.put("card", getOwner());
			map.put("change", empiricValue);
			NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_State_EmpiricValue,map);
			super.notifyObservers(info);
		}
	}
	
	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return (LifeCard) super.getOwner();
	}
	
	@Override
	public void action(Object... objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		Integer level = getLevel();
		level += 1;
		setLevel(level);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getPlayer());
		map.put("container", getOwner().getContainer());
		map.put("position", getOwner().getContainerPosition());
		map.put("card", getOwner());
		map.put("level", getLevel());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Action_Move,map);
		super.notifyObservers(info);
	}

}
