package org.cx.game.action;

/**
 * 升级
 * @author chenxian
 *
 */
public interface IUpgrade extends IAction {

	public Integer getLevel();
	
	public void setLevel(Integer level);
	
	public static final Integer BasicStandard = 100;
	
	public static final Integer BuildingStandard = 200;
	
	public static final Double DefaultBuildingRiseRatio = 2d;
	
	public static final Double DefaultProductRiseRatio = 2d;
	
	public static final Double DefaultLifeCardRiseRatio = 1.2;
	
	/**
	 * 升级需要耗费的资源数
	 * @return
	 */
	public Integer getStandard();
	
	public void setStandard(Integer standard);
	
	/**
	 * 更新标准
	 */
	public void updateStandard();
}
