package org.cx.game.policy;

import java.util.List;

/**
 * 优先级
 * @author chenxian
 *
 */
public interface IPriority {

	public Object getOwner();
	
	public void setOwner(Object owner);
	
	/**
	 * 计算PRI(优先级)
	 */
	public void refreshPri();
	
	public Integer getPri();
	
	public void setPri(int pri);
	
	/**
	 * 添加一组用于计算PRI的公式
	 * @param formulaList
	 */
	public void setFormulaList(List<IFormula> formulaList);
}
