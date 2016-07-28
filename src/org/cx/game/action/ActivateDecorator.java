package org.cx.game.action;

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

}
