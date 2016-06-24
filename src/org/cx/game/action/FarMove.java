package org.cx.game.action;

public class FarMove extends Move {

	private Integer tiredAttackScale;
	
	public FarMove(Integer tiredAttackScale) {
		super();
		// TODO Auto-generated constructor stub
		this.tiredAttackScale = tiredAttackScale;
	}

	public Integer getTiredAttackScale() {
		return tiredAttackScale;
	}

}
