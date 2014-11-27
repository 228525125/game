package org.cx.game.action;

public class AffectedDecorator extends ActionDecorator implements IAffected {

	private IAffected affected = null;
	
	public AffectedDecorator(IAffected affected) {
		super(affected);
		// TODO Auto-generated constructor stub
		this.affected = affected;
	}

}
