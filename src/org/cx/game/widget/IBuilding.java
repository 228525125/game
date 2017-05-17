package org.cx.game.widget;

import org.cx.game.core.IPlayer;

public interface IBuilding {

	public IPlace getOwner();
	
	public void setOwner(IPlace place);
	
	public IPlayer getBuildingPlayer();
	
	public void setBuildingPlayer(IPlayer player);

	public static final Integer TOWN = 1;
	
	public static final Integer BRIDGE = 2;
	
	public Integer getType();
}
