package org.cx.game.npc;

import java.util.List;

public interface IPriority {

	/**
	 * 计算PRI(优先级)
	 */
	public void makePri();
	
	public Integer getPri();
	
	public void setPri(int pri);
	
	/**
	 * 添加一组用于计算PRI的公式
	 * @param formulaList
	 */
	public void setFormulaList(List<IFormula> formulaList);
}
