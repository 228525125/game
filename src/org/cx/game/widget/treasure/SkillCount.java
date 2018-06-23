package org.cx.game.widget.treasure;

import org.cx.game.tools.CommonIdentifier;

public class SkillCount extends Resource implements IResource {

	public SkillCount() {
		// TODO Auto-generated constructor stub
	}
	
	public SkillCount(Integer count) {
		// TODO Auto-generated constructor stub
		add(CommonIdentifier.SkillCount, count);
	}
	
	public void add(Integer count){
		add(CommonIdentifier.SkillCount, count);
	}
	
	public Integer get(){
		return get(CommonIdentifier.SkillCount);
	}
	
	public Integer getCount(){
		return get();
	}
}
