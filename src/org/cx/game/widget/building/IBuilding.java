package org.cx.game.widget.building;

import java.util.List;

import org.cx.game.action.IUpgrade;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.Observable;
import org.cx.game.widget.IPlace;

public interface IBuilding {

	public String getName();

	public IPlace getOwner();
	
	public static final Integer Building = 1010;

	public static final Integer Town = 501;    //城镇
	
	public static final Integer Bridge = 502;  //桥
	
	public static final Integer Smithyt = 503; //铁匠铺
	
	public static final Integer Hieron = 504;  //神殿
	
	public static final Integer Village = 505; //村庄
	
	/**
	 * 类型：城镇/桥 等
	 * @return
	 */
	public Integer getType();
	
	/**
	 * 坐标
	 * @return
	 */
	public Integer getPosition();
	
	/**
	 * 占领者
	 * @return
	 */
	public IPlayer getPlayer();
	
	public void setPlayer(IPlayer player);
	
	/**
	 * 征税
	 * @return
	 */
	public Integer getTax();
	
	public void setTax(Integer tax);
	
	/**
	 * 升级标准
	 * @return
	 */
	public Integer getStandard();
	
	public void setStandard(Integer standard);
	
	/**
	 * 选项
	 * @return
	 */
	public List<IOption> getOptions();
	
	public void setOptions(List<IOption> options);
	
	/**
	 * 
	 * @param index 选项顺序号
	 * @return
	 */
	public IOption getOption(Integer index);
	
	/**
	 * 产品
	 * @return
	 */
	public List<IProduct> getProducts();
	
	public void setProducts(List<IProduct> products);
	
	/**
	 * 
	 * @param productType 产品类型
	 * @return
	 */
	public IProduct getProduct(Integer productType);
	
	public IUpgrade getUpgrade(); 
	
	/**
	 * 升级建筑
	 * @throws RuleValidatorException
	 */
	public void upgrade() throws RuleValidatorException;
}
