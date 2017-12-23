package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.card.LifeCard;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;

public class UpgradeLife extends Upgrade implements IUpgradeLife {
	
	private Integer empiricValue = 0;          //经验值
	
	public UpgradeLife() {
		// TODO Auto-generated constructor stub
		getRequirement().put(IPlayer.EmpiricValue, 100);
	}
	
	public Integer getEmpiricValue() {
		// TODO Auto-generated method stub
		return this.empiricValue;
	}
	
	public void setEmpiricValue(Integer empiricValue){
		if(!empiricValue.equals(this.empiricValue)){
			this.empiricValue = empiricValue;
		}
	}
	
	public void addToEmpiricValue(Integer empiricValue) {
		// TODO Auto-generated method stub
		if(!Integer.valueOf(0).equals(empiricValue)){
			this.empiricValue += empiricValue;
			
			Integer req = getRequirement().get(IPlayer.EmpiricValue);
			if(getEmpiricValue()>=req){
				try {
					action();
				} catch (RuleValidatorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void updateRequirement(){
		Integer riseRatio = getLevel()>1 ? IUpgrade.DefaultLifeCardRiseRatio*getLevel() : 100;
		for(String key : getRequirement().keySet()){
			Integer value = getRequirement().get(key);
			value = value * riseRatio / 100;
			getRequirement().put(key, value);
		}
	}
	
	public Integer getProcess() {
		// TODO Auto-generated method stub
		return getEmpiricValue()*100/getRequirement().get(IPlayer.EmpiricValue);
	}
	
	@Override
	public void setLevel(Integer level) {
		// TODO Auto-generated method stub
		if(!level.equals(getLevel())){
			super.setLevel(level);
			
			if(null!=getOwner()){
				getOwner().getAttack().updateExtraAtk();
				getOwner().getAttacked().updateExtraDef();		
			}
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
		
		/*
		 * 扣减升级所需经验值
		 */
		addToEmpiricValue(getRequirement().get(IPlayer.EmpiricValue));
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getPlayer());
		map.put("container", getOwner().getContainer());
		map.put("position", getOwner().getPosition());
		map.put("card", getOwner());
		map.put("level", getLevel());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Action_Upgrade,map);
		super.notifyObservers(info);
	}

}
