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
	
	/**
	 * 升级需要耗费的资源数
	 * @return
	 */
	public Integer getConsume();
	
	public void setConsume(Integer consume);
	
	/**
	 * 升级需要等待的回合数
	 * @return
	 */
	public Integer getWaitBout();
	
	public void setWaitBout(Integer bout);
	
	/**
	 * 当前升级进度
	 * @return
	 */
	public Integer getProcess();
}
