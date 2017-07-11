package org.cx.game.widget.building;

import java.util.List;

import org.cx.game.exception.RuleValidatorException;
import org.cx.game.exception.ValidatorException;
import org.cx.game.validator.IValidatable;
import org.cx.game.widget.IGround;

/**
 * 选项，例如Town的选项包含：升级、招募等
 * @author chenxian
 *
 */
public interface IOption extends IValidatable{

	public String getName();
	
	/**
	 * 使用间隔
	 * @return
	 */
	public Integer getSpacing();
	
	public void setSpacing(Integer spacing);
	
	/**
	 * 间隔剩余周期
	 * @return
	 */
	public Integer getSpacingRemain();
	
	public void setSpacingRemain(Integer spacingRemain);
	
	public IBuilding getOwner();
	
	public void setOwner(IBuilding building);
	
	/**
	 * 查询参数范围
	 * @param ground
	 * @return
	 */
	public List<Integer> getExecuteRange(IGround ground);
	
	/**
	 * 执行选项
	 * @param objects
	 */
	public void execute(Object...objects) throws RuleValidatorException;
}
