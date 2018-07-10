package org.cx.game.widget.building;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cx.game.action.Upgrade;
import org.cx.game.action.ActionProxyHelper;
import org.cx.game.action.IAction;
import org.cx.game.core.AbstractPlayer;
import org.cx.game.tools.I18n;
import org.cx.game.widget.AbstractPlace;
import org.cx.game.widget.treasure.Resource;

public abstract class AbstractBuilding {

	static final Integer Building_Status_Nothingness = 0;        //不存在
	static final Integer Building_Status_Build = 1;              //建造过程中
	static final Integer Building_Status_Complete = 2;           //完工
	
	private String name = null;
	private Integer type = 0;
	private Integer buildWait = 0;
	private Integer level = 1;
	private Integer levelLimit = 1;
	private Integer status = Building_Status_Nothingness;
	
	private AbstractPlace place = null;
	private AbstractPlayer player = null;
	private Resource consume = new Resource();
	private AbstractBuilding owner = null;
	private Upgrade upgrade = null;
	
	private List<AbstractOption> options = new ArrayList<AbstractOption>();
	private List<AbstractBuilding> buildings = new ArrayList<AbstractBuilding>();
	private List<Integer> needBuilding = new ArrayList<Integer>();
	private Map<Integer, String> upgradeRequirement = new HashMap<Integer, String>();
	
	public AbstractBuilding(Integer type) {
		// TODO Auto-generated constructor stub
		this.type = type;
	}
	
	/**
	 * 唯一标识
	 * @return
	 */
	public Integer getType() {
		// TODO Auto-generated method stub
		return this.type;
	}
	
	/**
	 * 分类
	 * @return
	 */
	public Integer getCategory() {
		String type = this.type.toString().substring(0, 3);
		return Integer.parseInt(type);
	}
	
	public String getName() {
		if(null==name)
			name = I18n.getMessage(this, this.type, "name");
		return name;
	}
	
	/**
	 * 状态，Nothingness / Build / Complete
	 * @return
	 */
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * 父建筑，例如 城堡；
	 * @return
	 */
	public AbstractBuilding getOwner() {
		// TODO Auto-generated method stub
		return owner;
	}
	
	public void setOwner(AbstractBuilding building) {
		// TODO Auto-generated method stub
		this.owner = building;
	}
	
	/**
	 * 所属位置
	 * @return
	 */
	public AbstractPlace getPlace() {
		// TODO Auto-generated method stub
		return this.place;
	}
	
	public void setPlace(AbstractPlace place) {
		// TODO Auto-generated method stub
		this.place = place;
		
		/*
		 * 主建筑物的位置，决定了子建筑物的位置
		 */
		for(AbstractBuilding building : getBuildings()){
			building.setPlace(place);
		}
	}
	
	public AbstractPlayer getPlayer() {
		// TODO Auto-generated method stub
		return this.player;
	}

	public void setPlayer(AbstractPlayer player) {
		// TODO Auto-generated method stub
		this.player = player;
	}
	
	/**
	 * 
	 * @return 内部建筑物
	 */
	public List<AbstractBuilding> getBuildings() {
		// TODO Auto-generated method stub
		return this.buildings;
	}
	
	/**
	 * 根据顺序号获取内部建筑
	 * @param index
	 * @return
	 */
	public AbstractBuilding getBuilding(Integer index) {
		// TODO Auto-generated method stub
		return this.buildings.get(index);
	}
	
	/**
	 * 添加子建筑物
	 * @param building
	 */
	public void addBuilding(AbstractBuilding building) {
		this.buildings.add(building);
		
		building.setPlace(getPlace());
	}
	
	/**
	 * 选项
	 * @return
	 */
	public List<AbstractOption> getOptions() {
		// TODO Auto-generated method stub
		return this.options;
	}
	
	public void setOptions(List<AbstractOption> options) {
		// TODO Auto-generated method stub
		for(AbstractOption option : options)
			addOption(option);
	}
	
	/**
	 * 
	 * @param index 选项顺序号
	 * @return
	 */
	public AbstractOption getOption(Integer index) {
		// TODO Auto-generated method stub
		return this.options.get(index);
	}
	
	public void addOption(AbstractOption option) {
		// TODO Auto-generated method stub
		option.setOwner(this);
		this.options.add(option);
	}
	
	public void setBuildWait(Integer bout) {
		// TODO Auto-generated method stub
		this.buildWait = bout;
	}
	
	/**
	 * 建造周期
	 * @return
	 */
	public Integer getBuildWait() {
		// TODO Auto-generated method stub
		return this.buildWait;
	}
	
	/**
	 * 前置建筑物
	 * @return 建筑物type
	 */
	public List<Integer> getNeedBuilding() {
		return needBuilding;
	}

	public void setNeedBuilding(List<Integer> needBuilding) {
		this.needBuilding = needBuilding;
	}
	
	/**
	 * 建造所需资源
	 * @return
	 */
	public Resource getConsume() {
		// TODO Auto-generated method stub
		return consume;
	}
	
	public void setConsume(Resource consume) {
		// TODO Auto-generated method stub
		this.consume = consume;
	}
	
	/**
	 * 建筑物等级上限
	 * @return
	 */
	public Integer getLevelLimit() {
		// TODO Auto-generated method stub
		return this.levelLimit;
	}
	
	public void setLevelLimit(Integer levelLimit) {
		// TODO Auto-generated method stub
		this.levelLimit = levelLimit;
	}
	
	public void setUpgradeRequirement(Map<Integer, String> upgradeRequirement) {
		this.upgradeRequirement = upgradeRequirement;
	}

	public Upgrade getUpgrade() {
		if(null==upgrade){
			Upgrade upgrade = new UpgradeBuilding(upgradeRequirement);
			upgrade.setLevel(level);
			upgrade.setLevelLimit(levelLimit);
			upgrade.setOwner(this);			
			this.upgrade = upgrade;
		}
		return upgrade;
	}
	
	/**
	 * 升级建筑
	 */
	public void upgrade() {
		// TODO Auto-generated method stub
		IAction action = new ActionProxyHelper(getUpgrade());
		action.action();
	}
	
	/**
	 * 摧毁
	 */
	public void demolish(){
		
	}
	
	/**
	 * 开始建造
	 */
	public void build() {
		// TODO Auto-generated method stub
		setStatus(Building_Status_Complete);
	}
	
	/*public Boolean isUpgrade() {
		// TODO Auto-generated method stub
		if(null!=getUpgrade()){
			return getUpgrade().getLevel()<getUpgrade().getLevelLimit();
		}
		return false;
	}*/
}
