package org.cx.game.widget.building;

import java.util.List;

import org.cx.game.core.IPlayer;
import org.cx.game.widget.IPlace;

public interface IBuilding {

	public String getName();
	
	/**
	 * 
	 * @return 位置
	 */
	public IPlace getOwner();
	
	public void setOwner(IPlace place);

	public static final Integer TOWN = 501;
	
	public static final Integer BRIDGE = 502;
	
	/**
	 * 类型：城镇/桥 等
	 * @return
	 */
	public Integer getType();
	
	/**
	 * 坐标
	 * @return
	 */
	public Integer getPosition();
	
	/**
	 * 占领者
	 * @return
	 */
	public IPlayer getPlayer();
	
	public void setPlayer(IPlayer player);
	
	/**
	 * 选项
	 * @return
	 */
	public List<IOption> getOptions();
	
	public void setOptions(List<IOption> options);
	
	/**
	 * 
	 * @param index 选项顺序号
	 * @return
	 */
	public IOption getOption(Integer index);
}
