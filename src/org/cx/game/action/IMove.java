package org.cx.game.action;

public interface IMove extends IAction{

	public static final Integer Type_Walk = 0;    //步行
	
	public static final Integer Type_Fly = 1;  //飞行
	
	public static final Integer Type_Flash = 2;  //瞬移
	
	public static final Integer Consume = 1; //单格移动消耗
	
	public static final Integer Energy_Max = 20;      //最大精力，表示可移动到任意位置
	
	public static final Integer Energy_Min = 0;       //最小精力，表示不可移动
	
	public static final Integer TurnoverRatio = 60; //资源转换率
	
	public Integer getType(); 
	
	public void setType(Integer type);
	
	/**
	 * 单格移动消耗
	 * @return
	 */
	public Integer getConsume();
	
	public void setConsume(Integer consume);
	
	public Boolean getMoveable();
	
	public void setMoveable(Boolean moveable);
	
	/**
	 * 精力，与移动范围相关
	 * @return
	 */
	public Integer getEnergy();

	public void setEnergy(Integer energy);
	
	/**
	 * 逃离成功率
	 * @return
	 */
	public Integer getFleeChance();
	
	public void setFleeChance(Integer fleeChance);
	
	public void addToEnergy(Integer energy);

}
