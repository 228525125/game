package org.cx.game.action;

import org.cx.game.card.LifeCard;

public interface IAffected extends IAction {
		
	@Override
	public LifeCard getOwner();
	
	/**
	 * 魔法伤害，当魔法造成伤害时，必须调用该方法
	 * @param harm 伤害值
	 */
	public void magicHarm(Integer harm);
}
