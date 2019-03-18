package org.cx.game.widget.building;

import org.cx.game.widget.IPlace;

public interface IBuilding {

	/**
	 * 不存在
	 */
	static final Integer Building_Status_Nothingness = 0;
	
	/**
	 * 建造过程中
	 */
	static final Integer Building_Status_Build = 1;
	
	/**
	 * 完工
	 */
	static final Integer Building_Status_Complete = 2;
	
	/**
	 * 唯一标识
	 * @return
	 */
	public Integer getType();
	

	/**
	 * 该方法在对象被创建后调用
	 */
	public void afterConstruct();
	
	/**
	 * 所属位置
	 * @return
	 */
	public IPlace getPlace();
	
	public void setPlace(IPlace place);
	
	public void setStatus(Integer status);
}
