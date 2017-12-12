package org.cx.game.widget.building;

import java.util.List;
import java.util.Map;

public class BuildingCall extends Building implements IBuilding {

	private Integer cardID = null;       //可招募单位
	private Integer nop = 0;             //当前可招募数量
	private Integer yield = null;        //产量/天
	
	public BuildingCall(Integer cardID, Integer yield, Integer buildingType) {
		// TODO Auto-generated constructor stub
		super(buildingType);
		
		this.cardID = cardID;
		this.yield = yield;
		
		IOption callOption = new OptionCall(cardID);
		
		addOption(callOption);
	}

	public Integer getCardID() {
		return cardID;
	}

	public Integer getNop() {
		return nop;
	}

	public Integer getYield() {
		return yield;
	}

	public void setYield(Integer yield) {
		this.yield = yield;
	}
	
	/**
	 * 产出
	 */
	public void output() {
		nop += yield;
	}
	
	@Override
	public void setPosition(Integer position) {
		// TODO Auto-generated method stub
		super.setPosition(position);
	}
	
	@Override
	public void setConsume(Map<String, Integer> consume) {
		// TODO Auto-generated method stub
		super.setConsume(consume);
	}
	
	@Override
	public void setBuildWait(Integer bout) {
		// TODO Auto-generated method stub
		super.setBuildWait(bout);
	}
	
	@Override
	public void setLevelLimit(Integer levelLimit) {
		// TODO Auto-generated method stub
		super.setLevelLimit(levelLimit);
	}
	
	@Override
	public void setNeedBuilding(List<Integer> needBuilding) {
		// TODO Auto-generated method stub
		super.setNeedBuilding(needBuilding);
	}
}
