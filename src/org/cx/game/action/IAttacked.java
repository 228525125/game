package org.cx.game.action;

import org.cx.game.card.LifeCard;

public interface IAttacked extends IAction {
	
	public LifeCard getOwner();
	
	/**
	 * 是否可以反击
	 * @return
	 */
	public Boolean getFightBack();
	
	public void setFightBack(Boolean fightBack);
	
	/**
	 * 护甲
	 * @return
	 */
	public Integer getArmour();
	
	public void setArmour(Integer armour);
	
	/*
	 * 取消护甲抵消伤害
	 */
	//public Integer addToArmour(Integer armour);
	
	/**
	 * 真实防御力 = 初始防御力 + 等级防御力 + 地形防御力 + 额外防御力
	 * @return
	 */
	public Integer getDef();
	
	public void setDef(Integer def);
	
	public void addToDef(Integer def);
	
	/**
	 * 地形防御力
	 * @return
	 */
	public Integer getLandformDef();

	public void setLandformDef(Integer landformDef);
	
	/**
	 * 装备防御力
	 * @return
	 */
	public Integer getArmourDef();
	
	public void updateArmourDef();
	
	/**
	 * 额外防御力
	 * @return
	 */
	public Integer getExtraDef();
	
	public void setExtraDef(Integer extraDef);
	
	/**
	 * 更新防御力
	 */
	public void updateDef();

}
