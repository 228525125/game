package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.corps.Corps;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.tools.PropertiesUtil;
import org.cx.game.widget.treasure.EmpiricValue;

public class UpgradeCorps extends Upgrade implements IUpgradeCorps {
	
	public UpgradeCorps(Map<Integer, String> requirement) {
		super(requirement);
		// TODO Auto-generated constructor stub
	}

	private EmpiricValue empiricValue = new EmpiricValue(0);          //经验值
	
	public EmpiricValue getEmpiricValue() {
		// TODO Auto-generated method stub
		return this.empiricValue;
	}
	
	public void addToEmpiricValue(EmpiricValue empiricValue) {
		// TODO Auto-generated method stub
		if(!empiricValue.isEmpty()){
			this.empiricValue.add(empiricValue);
			
			Integer req = getRequirement().get();
			if(getEmpiricValue().get()>=req){
				try {
					action();
				} catch (RuleValidatorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void addToEmpiricValue(Integer empiricValue) {
		// TODO Auto-generated method stub
		EmpiricValue ev = new EmpiricValue(empiricValue);
		addToEmpiricValue(ev);
	}
	
	@Override
	public EmpiricValue getRequirement() {
		// TODO Auto-generated method stub
		return (EmpiricValue) super.getRequirement();
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
	public Corps getOwner() {
		// TODO Auto-generated method stub
		return (Corps) super.getOwner();
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
		addToEmpiricValue(getRequirement());
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("position", getOwner().getPosition());
		map.put("card", getOwner());
		map.put("level", getLevel());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Corps_Upgrade,map);
		super.notifyObservers(info);
	}

}
