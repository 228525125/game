package org.cx.game.widget.building;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.cx.game.action.UpgradeBuilding;
import org.cx.game.action.IUpgrade;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.IRecover;
import org.cx.game.tools.I18n;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IPlace;
import org.cx.game.widget.treasure.IResource;
import org.cx.game.widget.treasure.Resource;

public class Building implements IBuilding, IRecover {

	private Integer position = 0;
	private String name = null;
	private IPlace place = null;
	private Integer type = 0;
	private Integer buildWait = 0;
	private Integer levelLimit = 1;
	private IPlayer player = null;
	private List<IOption> options = new ArrayList<IOption>();
	private List<IProduct> products = new ArrayList<IProduct>();
	private IResource consume = new Resource();
	
	private IBuilding owner = null;
	
	private Integer status = IBuilding.Building_Status_Nothingness;
	
	private List<Integer> needBuilding = new ArrayList<Integer>();
	
	private List<Map<IInterceptable, IIntercepter>> resetList = new ArrayList<Map<IInterceptable, IIntercepter>>();
	
	public Building(Integer buildingType) {
		// TODO Auto-generated constructor stub
		this.type = buildingType;
		
		IOption optionBuild = new OptionBuild();				
		addOption(optionBuild);
	}
	
	public String getName() {
		if(null==name)
			name = I18n.getMessage(this, this.type, "name");
		return name;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public void setBuildWait(Integer bout) {
		// TODO Auto-generated method stub
		this.buildWait = bout;
	}
	
	@Override
	public Integer getBuildWait() {
		// TODO Auto-generated method stub
		return this.buildWait;
	}
	
	@Override
	public Integer getLevelLimit() {
		// TODO Auto-generated method stub
		return this.levelLimit;
	}
	
	public void setLevelLimit(Integer levelLimit) {
		// TODO Auto-generated method stub
		this.levelLimit = levelLimit;
		
		if(1<levelLimit){
			IOption optionBuildingUpgrade = new OptionBuildingUpgrade();
			addOption(optionBuildingUpgrade);
		}
	}
	
	@Override
	public Boolean isUpgrade() {
		// TODO Auto-generated method stub
		if(null!=getUpgrade()){
			return getUpgrade().getLevel()<getUpgrade().getLevelLimit();
		}
		return false;
	}

	public List<Integer> getNeedBuilding() {
		return needBuilding;
	}

	public void setNeedBuilding(List<Integer> needBuilding) {
		this.needBuilding = needBuilding;
	}

	public Integer getPosition() {
		// TODO Auto-generated method stub
		return position;
	}
	
	@Override
	public void setPosition(Integer position) {
		// TODO Auto-generated method stub
		this.position = position;
	}

	@Override
	public IBuilding getOwner() {
		// TODO Auto-generated method stub
		return owner;
	}
	
	@Override
	public void setOwner(IBuilding building) {
		// TODO Auto-generated method stub
		this.owner = building;
	}
	
	@Override
	public IPlayer getPlayer() {
		// TODO Auto-generated method stub
		return this.player;
	}

	@Override
	public void setPlayer(IPlayer player) {
		// TODO Auto-generated method stub
		this.player = player;
	}

	@Override
	public Integer getType() {
		// TODO Auto-generated method stub
		return this.type;
	}
	
	@Override
	public List<IOption> getOptions() {
		// TODO Auto-generated method stub
		return this.options;
	}
	
	@Override
	public void setOptions(List<IOption> options) {
		// TODO Auto-generated method stub
		for(IOption option : options)
			addOption(option);
	}
	
	@Override
	public IOption getOption(Integer index) {
		// TODO Auto-generated method stub
		return this.options.get(index);
	}
	
	@Override
	public void addOption(IOption option) {
		// TODO Auto-generated method stub
		option.setOwner(this);
		this.options.add(option);
	}
	
	@Override
	public List<IProduct> getProducts() {
		// TODO Auto-generated method stub
		return this.products;
	}
	
	@Override
	public void setProducts(List<IProduct> products) {
		// TODO Auto-generated method stub
		for(IProduct product : products)
			product.setOwner(this);
		this.products = products;
	}
	
	@Override
	public IProduct getProduct(Integer productType) {
		// TODO Auto-generated method stub
		for(IProduct product : this.products)
			if(productType.equals(product.getType()))
				return product;
		return null;
	}
	
	private IUpgrade upgrade = null;

	public IUpgrade getUpgrade() {
		if(null==upgrade){
			IUpgrade upgrade = new UpgradeBuilding();
			upgrade.setLevelLimit(levelLimit);
			upgrade.setRequirement(consume);      //升级默认与建造相同，但这种方式可能会有问题
			upgrade.setOwner(this);
			this.upgrade = upgrade;
		}
		return upgrade;
	}
	
	@Override
	public void upgrade() throws RuleValidatorException {
		// TODO Auto-generated method stub
		this.upgrade.execute();
	}
	
	public void demolish(){
		resetIntercepter();
	}
	
	@Override
	public void build() {
		// TODO Auto-generated method stub
		setStatus(IBuilding.Building_Status_Complete);
	}
	
	@Override
	public IResource getConsume() {
		// TODO Auto-generated method stub
		return consume;
	}
	
	@Override
	public void setConsume(IResource consume) {
		// TODO Auto-generated method stub
		this.consume = consume;
	}
	
	public void resetIntercepter() {
		// TODO Auto-generated method stub
		resetIntercepter(IIntercepter.Level_Current);
	}
	
	@Override
	public void resetIntercepter(Integer level) {
		// TODO Auto-generated method stub
		for(Map<IInterceptable, IIntercepter> map : resetList){
			for(Entry<IInterceptable, IIntercepter> entry : map.entrySet()){
				IInterceptable interceptable = entry.getKey();
				IIntercepter intercepter = entry.getValue();	
				if(level.equals(intercepter.getLevel()))
					interceptable.deleteIntercepter(intercepter);
			}
		}
	}
	
	public void recordIntercepter(IInterceptable interceptable, IIntercepter intercepter){
		interceptable.addIntercepter(intercepter);
		Map entry = new HashMap<IInterceptable, IIntercepter>();
		entry.put(interceptable, intercepter);
		resetList.add(entry);
	}
}
