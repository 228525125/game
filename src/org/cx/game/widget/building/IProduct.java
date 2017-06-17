package org.cx.game.widget.building;

import org.cx.game.action.IUpgrade;
import org.cx.game.exception.RuleValidatorException;

public interface IProduct {

	public String getName();
	
	public IBuilding getOwner();
	
	public void setOwner(IBuilding building);
	
	/**
	 * 刀剑
	 */
	public static final Integer Product_Sword = 151;
	
	/**
	 * 护甲
	 */
	public static final Integer Product_Armour = 152;
	
	/**
	 * 信仰
	 */
	public static final Integer Product_Faith = 153;
	
	/**
	 * 产品类型
	 * @return
	 */
	public Integer getType();
	
	public IUpgrade getUpgrade(); 
	
	/**
	 * 产品升级
	 * @throws RuleValidatorException
	 */
	public void upgrade() throws RuleValidatorException;
}
