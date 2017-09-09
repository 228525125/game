package org.cx.game.policy;

import java.util.List;

import org.cx.game.exception.PolicyValidatorException;

/**
 * 策略
 * @author chenxian
 *
 */
public interface IPolicy {

	public final static Integer PRI_Max = 200;
	public final static Integer PRI_Very = 180;
	public final static Integer PRI_High = 150;
	public final static Integer PRI_Middle = 100;
	public final static Integer PRI_Default = 100;
	public final static Integer PRI_Low = 50;
	public final static Integer PRI_Bottom = 20;
	public final static Integer PRI_Min = 0;
	
	public IPolicyGroup getOwner();
	
	public void setOwner(IPolicyGroup owner);
	
	/**
	 * 优先值
	 * @return
	 */
	public Integer getPri();
	
	public void setPri(int pri);
	
	/**
	 * 每格策略都有条件需要满足才能执行，这个值判断是否满足
	 * @return
	 */
	public Boolean isReady();
	
	public void setReady(Boolean ready);
	
	/**
	 * 运算策略逻辑，并准备执行策略的条件
	 */
	public void calculate();
	
	/**
	 * 执行动作
	 */
	public void execute();
}
