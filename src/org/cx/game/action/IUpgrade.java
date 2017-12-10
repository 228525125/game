package org.cx.game.action;

import java.util.Map;

/**
 * 升级
 * @author chenxian
 *
 */
public interface IUpgrade extends IAction {

	public Integer getLevel();
	
	public void setLevel(Integer level);
	
	public Integer getLevelLimit();
	
	public void setLevelLimit(Integer levelLimit);
	
	public static final Integer DefaultLifeCardUpgradeRequirement = 100;
	
	public static final Integer DefaultBuildingUpgradeGoldRequirement = 100;
	
	public static final Integer DefaultProductUpgradeGoldRequirement = 100;

	public static final Integer DefaultBuildingRiseRatio = 200;
	
	public static final Integer DefaultProductRiseRatio = 200;
	
	public static final Integer DefaultLifeCardRiseRatio = 120;
	
	/**
	 * 升级需要耗费的资源数[类型][数量]
	 * @return
	 */
	public Map<String,Integer> getRequirement();
	
	public void setRequirement(Map<String,Integer> standard);
	
	/**
	 * 更新标准
	 */
	public void updateRequirement();
}
