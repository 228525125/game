package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.action.IUpgradeSkill;
import org.cx.game.action.IUpgrade;
import org.cx.game.action.Upgrade;
import org.cx.game.card.HeroCard;
import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.ISkill;
import org.cx.game.core.ContextFactory;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.widget.treasure.SkillCount;

public class UpgradeSkill extends Upgrade implements IUpgradeSkill {
	
	public UpgradeSkill(Map<Integer, String> requirement) {
		super(requirement);
		// TODO Auto-generated constructor stub
	}

	@Override
	public SkillCount getRequirement() {
		// TODO Auto-generated method stub
		SkillCount sc = new SkillCount(-1);
		return sc;
	}
	
	@Override
	public ISkill getOwner() {
		// TODO Auto-generated method stub
		return (ISkill) super.getOwner();
	}
	
	@Override
	public void action(Object... objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		
		Integer level = getLevel();
		level += 1;
		setLevel(level);
		
		/*
		 * 扣除技能点
		 */
		HeroCard hero = (HeroCard) getOwner().getOwner();
		IUpgradeHero up = hero.getUpgrade();
		up.addToSkillCount(getRequirement());
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getOwner().getPlayer());
		map.put("container", getOwner().getOwner().getContainer());
		map.put("position", getOwner().getOwner().getPosition());
		map.put("card", getOwner().getOwner());
		map.put("level", getLevel());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Skill_Upgrade,map);
		super.notifyObservers(info);
	}
}