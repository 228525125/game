package org.cx.game.action;

import org.cx.game.card.LifeCard;

public interface IMove extends IAction{

	public static final Integer Type_Walk = 141;    //步行
	
	public static final Integer Type_Equitation = 142;  //骑行
	
	public static final Integer Type_Drive = 143;   //驾驶
	
	public static final Integer Type_Fly = 144;  //飞行
	
	public static final Integer Type_Flash = 145;  //传送
	
	public static final Integer Consume = 1; //单格移动消耗
	
	public static final Integer Energy_Max = 20;      //最大精力，表示可移动到任意位置
	
	public static final Integer Energy_Min = 0;       //最小精力，表示不可移动
	
	public static final Integer TurnoverRatio = 60; //资源转换率
	
	/**
	 * 单格移动消耗
	 * @return
	 */
	public Integer getConsume();
	
	/**
	 * 移动状态
	 * @return
	 */
	public Boolean getMoveable();
	
	public void setMoveable(Boolean moveable);
	
	/**
	 * 移动方式
	 * @return
	 */
	public Integer getType(); 
	
	public void setType(Integer type);
	
	public void changeType(Integer type);
	
	/**
	 * 精力，与移动范围相关
	 * @return
	 */
	public Integer getEnergy();

	public void setEnergy(Integer energy);
	
	public void addToEnergy(Integer energy);
	
	/**
	 * 逃离成功率
	 * @return
	 */
	public Integer getFlee();
	
	public void setFlee(Integer fleeChance);
	
	public void addToFlee(Integer fleeChance);
	
	/**
	 * 隐身状态
	 * @return
	 */
	public Boolean getHide();
	
	public void setHide(Boolean hide);
	
	public void changeHide(Boolean hide);
	
	public LifeCard getOwner();

}
