package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.exception.RuleValidatorException;
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
		if(!skillCount.equals(this.skillCount)){
			skillCount = skillCount<0 ? 0 : skillCount;
			this.skillCount = skillCount;
		}
	}
	
	@Override
	public void action(Object... objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		super.action(objects);
		
		/*
		 * 英雄每升一级，增加1个技能点；
		 */
		Integer skillCount = getSkillCount();
		setSkillCount(++skillCount);
	}
}
