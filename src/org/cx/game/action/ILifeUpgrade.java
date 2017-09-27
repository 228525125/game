package org.cx.game.action;

import org.cx.game.card.LifeCard;

public interface ILifeUpgrade extends IUpgrade {

	public Integer getEmpiricValue();
	
	public void setEmpiricValue(Integer empiricValue);
	
	public void addToEmpiricValue(Integer empiricValue);
	
	public Integer getSkillCount();
	
	public void setSkillCount(Integer skillCount);
	
	public void addToSkillCount(Integer skillCount);
	
	@Override
	public LifeCard getOwner();
}
