package org.cx.game.widget;

public interface IBuilding {

	public IPlace getOwner();
	
	public void setOwner(IPlace place);

	public static final Integer TOWN = 1;
	
	public static final Integer BRIDGE = 2;
	
	public Integer getType();
}
