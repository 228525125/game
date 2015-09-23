package org.cx.game.card.skill.ext.forest;

import org.cx.game.card.LifeCard;

/**
 * 狗头人围猎
 * @author chenxian
 *
 */
public class DogHuntUnits extends HuntUnits {

	private Integer defScale = 0;
	private Double upImmuneDamageRatio = 0d;
	
	public DogHuntUnits(Integer style, Integer range, Integer defScale) {
		super(style, range);
		// TODO Auto-generated constructor stub
		this.defScale = defScale;
	}
	
	@Override
	public void before(Object[] args) {
		// TODO Auto-generated method stub
		affect();
	}
	
	@Override
	public void after(Object[] args) {
		// TODO Auto-generated method stub
		affect();
	}
	
	@Override
	public void affect(Object... objects) {
		// TODO Auto-generated method stub
		super.affect(objects);
		
		Double immuneDamageRatio = getOwner().getAttacked().getImmuneDamageRatio();
		upImmuneDamageRatio = defScale.doubleValue()*getNumber() - upImmuneDamageRatio;
		getOwner().getAttacked().setImmuneDamageRatio(immuneDamageRatio + upImmuneDamageRatio);
	}
	
	@Override
	public void setOwner(LifeCard life) {
		// TODO Auto-generated method stub
		super.setOwner(life);
		
		life.getCall().addIntercepter(this);
		life.getAttacked().addIntercepter(this);
		life.getMove().addIntercepter(this);
	}
}
