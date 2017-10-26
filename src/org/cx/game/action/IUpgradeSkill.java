package org.cx.game.action;
import org.cx.game.action.IUpgrade;
import org.cx.game.card.skill.ISkill;


public interface IUpgradeSkill extends IUpgrade {

	public ISkill getOwner();
}
