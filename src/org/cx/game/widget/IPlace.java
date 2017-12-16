package org.cx.game.widget;

import java.util.List;

import org.cx.game.card.LifeCard;
import org.cx.game.card.trick.ITrick;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.observer.Observable;
import org.cx.game.widget.building.IBuilding;
import org.cx.game.widget.treasure.ITreasure;

/**
 * 战场位置
 * @author chenxian
 *
 */
public interface IPlace extends IInterceptable, Observable{
	
	public IContainer getContainer();

	public void in(LifeCard life);
	
	public LifeCard out();
	
	public LifeCard getLife();
	
	public ICemetery getCemetery();
	
	public ITrickList getTrickList();
	
	/**
	 * 坐标系位置
	 * @return
	 */
	public Integer getPosition();
	
	/**
	 * 距离指定位置相隔步数
	 * @param position 指定位置
	 * @param moveType 移动类型
	 * @return
	 */
	public Integer getStep(IPlace place, Integer moveType);
	
	/**
	 * 是否可用，有一些因为地形原因而导致不可用(暂时没用)
	 * @return
	 */
	public Boolean getDisable();
	
	public void setDisable(Boolean disable);
	
	/**
	 * 位置上是否为空
	 * @return
	 */
	public Boolean getEmpty();
	
	public void setEmpty(Boolean empty);
	
	/**
	 * 建筑物
	 * @return 
	 */
	public IBuilding getBuilding();
	
	public void setBuilding(IBuilding building);
	
	/**
	 * 草地
	 */
	public static final Integer Landform_Sward = 401;
	
	/**
	 * 丘林
	 */
	public static final Integer Landform_Massif = 402;
	
	/**
	 * 森林
	 */
	public static final Integer Landform_Forest = 403;
	
	/**
	 * 河
	 */
	public static final Integer Landform_River = 404;
	
	/**
	 * 山
	 */
	public static final Integer Landform_Hill = 405;
	
	/**
	 * 沼泽
	 */
	public static final Integer Landform_marsh = 406;
	
	/**
	 * 地形
	 * @return
	 */
	public Integer getLandform();
	
	public void setLandform(Integer langform);
	
	/**
	 * 放置物品
	 */
	public void setTreasure(ITreasure treasure);
	
	public ITreasure getTreasure();
	
}
