package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.widget.treasure.IResource;
import org.cx.game.widget.treasure.Resource;

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

	private IResource requirement = new Resource();
	
	@Override
	public IResource getRequirement() {
		// TODO Auto-generated method stub
		return requirement;
	}
	
	@Override
	public void setRequirement(IResource requirement) {
		// TODO Auto-generated method stub
		this.requirement = requirement;
	}

}
