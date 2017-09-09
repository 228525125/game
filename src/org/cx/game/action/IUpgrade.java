package org.cx.game.action;

/**
 * 升级
 * @author chenxian
 *
 */
public interface IUpgrade extends IAction {

	public Integer getLevel();
	
	public void setLevel(Integer level);
	
	public static final Integer BasicConsume = 100;
	
	public static final Integer BuildingConsume = 200;
	
	/**
	 * 升级需要耗费的资源数
	 * @return
	 */
	public Integer getConsume();
	
	public void setConsume(Integer consume);
}
