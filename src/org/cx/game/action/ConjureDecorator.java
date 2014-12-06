package org.cx.game.action;

public class ConjureDecorator extends ActionDecorator implements IConjure {

	private IConjure conjure;
	
	public ConjureDecorator(IConjure conjure) {
		super(conjure);
		// TODO Auto-generated constructor stub
		this.conjure = conjure;
	}

	@Override
	public Integer getPower() {
		// TODO Auto-generated method stub
		return this.conjure.getPower();
	}

	@Override
	public void setPower(Integer power) {
		// TODO Auto-generated method stub
		this.conjure.setPower(power);
	}

}
