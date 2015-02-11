package org.cx.game.policy;

/**
 * 用于计算PRI的公式
 * @author chenxian
 *
 */
public interface IFormula {

	public IPriority getPriority();
	
	public void setPriority(IPriority owner);
	
	/**
	 * 执行公式
	 * @return 计算结果是一个pri值
	 */
	public Integer execute();
}
