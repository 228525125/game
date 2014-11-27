package org.cx.game.action;

public class ConjureDecorator extends ActionDecorator implements IConjure {

	private IConjure conjure;
	
	public ConjureDecorator(IConjure conjure) {
		super(conjure);
		// TODO Auto-generated constructor stub
		this.conjure = conjure;
	}

}
