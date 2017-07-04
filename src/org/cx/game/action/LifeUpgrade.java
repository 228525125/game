package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.rule.IRule;
import org.cx.game.rule.UpgradeRule;

public class LifeUpgrade extends Upgrade implements IUpgrade {
	
	private Integer empiricValue = 0;          //经验值
	private Integer consume = IUpgrade.BasicConsume; 
	private Integer skillCount = 0;            //技能点
	
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
	
	@Override
	public Integer getConsume() {
		// TODO Auto-generated method stub
		return this.consume+getLevel()*120/100;
	}
	
	@Override
	public void setConsume(Integer consume) {
		// TODO Auto-generated method stub
		this.consume = consume;
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
	
	/**
	 * 技能点
	 * @return
	 */
	public Integer getSkillCount() {
		return skillCount;
	}

	public void setSkillCount(Integer skillCount) {
		this.skillCount = skillCount;
	}
	
	public void addToSkillCount(Integer skillCount){
		if(0<skillCount){
			this.skillCount += skillCount;
			this.skillCount = this.skillCount < 0 ? 0 : this.skillCount;
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", getOwner().getPlayer());
			map.put("container", getOwner().getContainer());
			map.put("card", getOwner());
			map.put("change", skillCount);
			map.put("position", getOwner().getContainerPosition());
			NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_State_Range,map);
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
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Action_Upgrade,map);
		super.notifyObservers(info);
	}

}
