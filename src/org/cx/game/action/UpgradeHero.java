package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.observer.NotifyInfo;

public class UpgradeHero extends UpgradeLife implements IUpgradeHero {

	private Integer skillCount = 0;            //技能点
	
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
			map.put("position", getOwner().getPosition());
			NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_State_Range,map);
			super.notifyObservers(info);
		}
	}
}
