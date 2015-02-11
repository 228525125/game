package org.cx.game.policy;

import org.cx.game.card.ICard;

public class LevelFormula implements IFormula {

	private final static Integer Level_Difference = 10; 
	private IPriority priority = null;
	
	@Override
	public Integer execute() {
		// TODO Auto-generated method stub
		Integer pri = 0;
		if (priority.getOwner() instanceof ICard) {
			ICard card = (ICard) priority.getOwner();
			pri += card.getStar()*Level_Difference;
		}
		return pri;
	}

	@Override
	public IPriority getPriority() {
		// TODO Auto-generated method stub
		return priority;
	}

	@Override
	public void setPriority(IPriority priority) {
		// TODO Auto-generated method stub
		this.priority = priority;
	}
}
