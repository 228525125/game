package org.cx.game.action;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;

public class ActivateDecorator extends ActionDecorator implements IActivate {

	private IActivate activate = null;
	
	public ActivateDecorator(IActivate activate) {
		super(activate);
		// TODO Auto-generated constructor stub
		this.activate = activate;
	}

	@Override
	public Boolean getActivation() {
		// TODO Auto-generated method stub
		return activate.getActivation();
	}

	@Override
	public void setActivation(Boolean activate) {
		// TODO Auto-generated method stub
		this.activate.setActivation(activate);
	}
	
	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return this.activate.getOwner();
	}

	@Override
	public Integer getSpeed() {
		// TODO Auto-generated method stub
		return activate.getSpeed();
	}

	@Override
	public void setSpeed(Integer speed) {
		// TODO Auto-generated method stub
		activate.setSpeed(speed);
	}

	@Override
	public void addToSpeed(Integer speed) {
		// TODO Auto-generated method stub
		activate.addToSpeed(speed);
	}

	@Override
	public Integer getVigour() {
		// TODO Auto-generated method stub
		return activate.getVigour();
	}

	@Override
	public void addToVigour(Integer vigour) {
		// TODO Auto-generated method stub
		activate.addToVigour(vigour);
	}

}
