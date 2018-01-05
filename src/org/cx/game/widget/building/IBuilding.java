package org.cx.game.widget.building;

import java.util.List;
import java.util.Map;

import org.cx.game.action.IUpgrade;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.Observable;
import org.cx.game.widget.treasure.IResource;

public interface IBuilding {

	public String getName();
	
	/**
	 * 父建筑，例如 城堡；
	 * @return
	 */
	public IBuilding getOwner();
	
	public void setOwner(IBuilding building);
	
	public static final Integer Building = 1010;
	
	public static final Integer Building_Bridge = 502;  //桥
	
	public static final Integer Building_Smithyt = 503; //铁匠铺
	
	public static final Integer Building_Hieron = 504;  //神殿
	
	public static final Integer Building_Village = 505; //村庄
	
	public static final Integer Building_Chengshi = 601; //城市
	
	public static final Integer Building_Ganglou = 602; //岗楼
	
	public static final Integer Building_Jianta = 603; //箭塔
	
	public static final Integer Building_Shijiuta = 604; //狮鹫塔
	
	public static final Integer Building_Bingying = 605; //兵营
	
	public static final Integer Building_Siyuan = 606; //寺院
	
	public static final Integer Building_Mapeng = 607; //马棚
	
	public static final Integer Building_Xunlianchang = 608; //训练场
	
	public static final Integer Building_Status_Nothingness = 0;        //不存在
	public static final Integer Building_Status_Build = 1;              //建造过程中
	public static final Integer Building_Status_Complete = 2;           //完工
	
	/**
	 * 类型：城镇/桥 等
	 * @return
	 */
	public Integer getType();
	
	/**
	 * 状态
	 * @return
	 */
	public Integer getStatus();
	
	public void setStatus(Integer status);
	
	/**
	 * 建造周期
	 * @return
	 */
	public Integer getBuildWait();
	
	/**
	 * 前置建筑物
	 * @return
	 */
	public List<Integer> getNeedBuilding();
	
	/**
	 * 建筑物等级上限
	 * @return
	 */
	public Integer getLevelLimit();
	
	/**
	 * 坐标
	 * @return
	 */
	public Integer getPosition();
	
	public void setPosition(Integer position);
	
	/**
	 * 占领者
	 * @return
	 */
	public IPlayer getPlayer();
	
	public void setPlayer(IPlayer player);
	
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
	 * 添加一个选项，它区别于getOptions().add()，它增加了setOwner
	 */
	public void addOption(IOption option);
	
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
	
	/**
	 * 摧毁
	 */
	public void demolish();
	
	/**
	 * 开始建造
	 */
	public void build();
	
	/**
	 * 建造材料
	 * @return
	 */
	public IResource getConsume();
	
	public void setConsume(IResource consume);
	
	/**
	 * 是否可升级，即当前等级小于等级上限
	 * @return
	 */
	public Boolean isUpgrade();
}
