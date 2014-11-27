package org.cx.game.card.skill.ext;

import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.SimpleBuff;
import org.cx.game.observer.NotifyInfo;

public class GambleBuff extends SimpleBuff {
	
	private Double downImmuneDamageRatio = 0d;
	private Integer speedChance = 0;
	
	/**
	 * 
	 * @param bout
	 * @param style
	 * @param type
	 * @param func
	 * @param downImmuneDamageRatio 免伤比降低值 
	 * @param life
	 */
	public GambleBuff(Integer bout, Integer style, Integer type, Integer func, Double downImmuneDamageRatio, LifeCard life) {
		super(bout, style, type, func, life);
		// TODO Auto-generated constructor stub
		this.downImmuneDamageRatio = downImmuneDamageRatio;
		this.speedChance = life.getAttack().getSpeedChance();
	}

	@Override
	public void before(Object[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void effect() {
		// TODO Auto-generated method stub
		super.effect();
		
		affect();		
	}
	
	@Override
	public void affect(Object... objects) {
		// TODO Auto-generated method stub
		addToKeepImmuneDamageRatio(downImmuneDamageRatio);
		getOwner().getAttack().setSpeedChance(0);
		
		super.affect(objects);
	}
}
