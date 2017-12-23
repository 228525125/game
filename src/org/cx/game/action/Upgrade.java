package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

public abstract class Upgrade extends Action implements IUpgrade {

	private Integer level = 1;
	private Integer levelLimit = 1;
	
	@Override
	public Integer getLevel() {
		// TODO Auto-generated method stub
		return this.level;
	}

	@Override
	public void setLevel(Integer level) {
		// TODO Auto-generated method stub
		if(!level.equals(this.level)){
			this.level = level;
			
			if(null!=getOwner())
				updateRequirement();
		}
	}
	
	public Integer getLevelLimit() {
		return levelLimit;
	}

	public void setLevelLimit(Integer levelLimit) {
		if(!levelLimit.equals(this.levelLimit)){
			this.levelLimit = levelLimit;
		}
	}

	private Map<String,Integer> requirement = new HashMap<String,Integer>();
	
	@Override
	public Map<String,Integer> getRequirement() {
		// TODO Auto-generated method stub
		return requirement;
	}
	
	@Override
	public void setRequirement(Map<String,Integer> requirement) {
		// TODO Auto-generated method stub
		this.requirement = requirement;
	}

}
