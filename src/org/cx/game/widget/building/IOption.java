package org.cx.game.widget.building;

import java.util.List;

import org.cx.game.action.Execute;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.validator.IValidatable;
import org.cx.game.widget.IGround;

/**
 * 选项，例如Town的选项包含：升级、招募等
 * @author chenxian
 *
 */
public interface IOption extends IValidatable {

	public String getName();
	
	/**
	 * 选项参数
	 * @return 执行数量
	 */
	public Integer getNumber();
	
	public void setNumber(Integer number);
	
	/**
	 * 选项被执行后，下一次可执行需间隔的回合数
	 * @param spacingWait 间隔回合数
	 */
	public void setSpacingWait(Integer spacingWait);
	
	public Integer getSpacingWait();
	
	/**
	 * 选项需要等待一定回合后才被执行
	 * @param executeWait 等待回合数
	 */
	public void setExecuteWait(Integer executeWait);
	
	public Integer getExecuteWait();
	
	/**
	 * 间隔剩余回合数
	 * @return
	 */
	public Integer getSpacingRemainBout();
	
	/**
	 * 距离执行完成还有多少回合
	 * @return
	 */
	public Integer getExecuteRemainBout();
	
	/**
	 * 开始执行间隔周期
	 */
	public void cooling();
	
	/**
	 * 是否可以执行
	 * @return
	 */
	public Boolean getAllow();
	
	public void setAllow(Boolean allow);
	
	public IBuilding getOwner();
	
	public void setOwner(IBuilding building);
	
	/**
	 * 查询执行范围
	 * @param ground
	 * @return
	 */
	public List<Integer> getExecuteRange(IGround ground);
	
	public Execute getExecute();
	
	/**
	 * 执行选项
	 * @param objects
	 */
	public void execute(Object [] objects) throws RuleValidatorException;
}
