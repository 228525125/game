package org.cx.game.action;

import org.cx.game.card.LifeCard;

public class RetaliateDecorator extends ActionDecorator implements IRetaliate {

	private IRetaliate retaliate = null;
	
	public RetaliateDecorator(final IRetaliate retaliate) {
		// TODO Auto-generated constructor stub
		super(retaliate);
		this.retaliate = retaliate;
		
		setParameterTypeValidator(new Class[]{LifeCard.class});
	}

	@Override
	public Boolean getFightBack() {
		// TODO Auto-generated method stub
		return this.retaliate.getFightBack();
	}

	@Override
	public void setFightBack(Boolean fightBack) {
		// TODO Auto-generated method stub
		this.retaliate.setFightBack(fightBack);
	}
	
	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return this.retaliate.getOwner();
	}

}
