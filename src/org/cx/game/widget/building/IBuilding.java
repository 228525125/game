package org.cx.game.widget.building;

import java.util.List;
import java.util.Map;

import org.cx.game.action.Upgrade;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.Observable;
import org.cx.game.widget.AbstractPlace;
import org.cx.game.widget.treasure.IResource;

public interface IBuilding {
	
	static final Integer Building_Status_Nothingness = 0;        //不存在
	static final Integer Building_Status_Build = 1;              //建造过程中
	static final Integer Building_Status_Complete = 2;           //完工

	public String getName();
	
	/**
	 * 父建筑，例如 城堡；
	 * @return
	 */
	public IBuilding getOwner();
	
	public void setOwner(IBuilding building);
	
	/**
	 * 类型：城镇/桥 等
	 * @return
	 */
	public Integer getType();
	
	/**
	 * 状态
	 * @return
	 */
	public Integer getStatus();
	
	public void setStatus(Integer status);
	
	/**
	 * 建造周期
	 * @return
	 */
	public Integer getBuildWait();
	
	/**
	 * 前置建筑物
	 * @return
	 */
	public List<Integer> getNeedBuilding();
	
	/**
	 * 
	 * @return 内部建筑物
	 */
	public List<IBuilding> getBuildings();
	
	/**
	 * 根据顺序号获取内部建筑
	 * @param index
	 * @return
	 */
	public IBuilding getBuilding(Integer index);
	
	/**
	 * 建筑物等级上限
	 * @return
	 */
	public Integer getLevelLimit();
	
	/**
	 * 所属位置
	 * @return
	 */
	public AbstractPlace getPlace();
	
	public void setPlace(AbstractPlace place);
	
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
	
	/**
	 * 添加一个选项，它区别于getOptions().add()，它增加了setOwner
	 */
	public void addOption(IOption option);
	
	public Upgrade getUpgrade(); 
	
	/**
	 * 升级建筑
	 * @throws RuleValidatorException
	 */
	public void upgrade();
	
	/**
	 * 摧毁
	 */
	public void demolish();
	
	/**
	 * 开始建造
	 */
	public void build();
	
	/**
	 * 建造材料
	 * @return
	 */
	public IResource getConsume();
	
	public void setConsume(IResource consume);
	
	/**
	 * 是否可升级，即当前等级小于等级上限
	 * @return
	 */
	public Boolean isUpgrade();
}
