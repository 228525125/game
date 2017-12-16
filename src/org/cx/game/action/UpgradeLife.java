package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.card.LifeCard;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;

public class UpgradeLife extends Upgrade implements IUpgradeLife {
	
	private Integer empiricValue = 0;          //经验值
	
	public Integer getEmpiricValue() {
		// TODO Auto-generated method stub
		return this.empiricValue;
	}
	
	public Integer getProcess() {
		// TODO Auto-generated method stub
		return getEmpiricValue()*100/getRequirement().get(IPlayer.EmpiricValue);
	}
	
	public void updateRequirement(){
		Integer riseRatio = getLevel()>1 ? IUpgrade.DefaultLifeCardRiseRatio*getLevel() : 100;
		for(String key : getRequirement().keySet()){
			Integer value = getRequirement().get(key);
			value = value * riseRatio / 100;
			getRequirement().put(key, value);
		}
	}
	
	public void setEmpiricValue(Integer empiricValue){
		this.empiricValue = empiricValue;
	}
	
	public void addToEmpiricValue(Integer empiricValue) {
		// TODO Auto-generated method stub
		if(!Integer.valueOf(0).equals(empiricValue)){
			this.empiricValue += empiricValue;
			
			/*Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", getOwner().getPlayer());
			map.put("container", getOwner().getContainer());
			map.put("position", getOwner().getContainerPosition());
			map.put("card", getOwner());
			map.put("change", empiricValue);
			NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_State_EmpiricValue,map);
			super.notifyObservers(info);*/
		}
	}
	
	@Override
	public Map<String, Integer> getRequirement() {
		// TODO Auto-generated method stub
		if(super.getRequirement().isEmpty())
			super.getRequirement().put(IPlayer.EmpiricValue, IUpgrade.DefaultLifeCardUpgradeRequirement);
		return super.getRequirement();
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
		map.put("position", getOwner().getPosition());
		map.put("card", getOwner());
		map.put("level", getLevel());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Action_Upgrade,map);
		super.notifyObservers(info);
	}
	
	@Override
	public void setLevel(Integer level) {
		// TODO Auto-generated method stub
		super.setLevel(level);
		
		if(null!=getOwner()){
			getOwner().getAttack().updateAtk();
			getOwner().getAttacked().updateDef();
			getOwner().getDeath().updateHpLimit();
			//getOwner().getCall().updateConsume();
		}
	}

}
