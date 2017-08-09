package org.cx.game.widget.building;

import java.util.ArrayList;
import java.util.List;

import org.cx.game.action.IUpgrade;
import org.cx.game.action.UpgradeDecorator;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.tools.I18n;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IPlace;

public class Building implements IBuilding {

	private Integer position = 0;
	private String name = null;
	private IPlace place = null;
	private Integer type = 0;
	private IPlayer player = null;
	private Integer tax = 0;
	private List<IOption> options = new ArrayList<IOption>();
	private List<IProduct> products = new ArrayList<IProduct>();
	
	public Building(Integer buildingType, Integer position) {
		// TODO Auto-generated constructor stub
		this.type = buildingType;
		this.position = position;
	}
	
	public String getName() {
		if(null==name)
			name = I18n.getMessage(this, this.type, "name");
		return name;
	}
	
	public Integer getPosition() {
		// TODO Auto-generated method stub
		return position;
	}
	
	@Override
	public IPlace getOwner() {
		// TODO Auto-generated method stub
		if(null==place){
			IGround ground = getPlayer().getGround();
			place = ground.getPlace(getPosition());
		}
		return place;
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
	public Integer getTax() {
		// TODO Auto-generated method stub
		return this.tax;
	}
	
	@Override
	public void setTax(Integer tax) {
		// TODO Auto-generated method stub
		this.tax = tax;
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
			option.setOwner(this);
		this.options = options;
	}
	
	@Override
	public IOption getOption(Integer index) {
		// TODO Auto-generated method stub
		return this.options.get(index);
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
			IUpgrade upgrade = new BuildingUpgrade();
			upgrade.setOwner(this);
			this.upgrade = new UpgradeDecorator(upgrade);
		}
		return upgrade;
	}

	public void setUpgrade(IUpgrade upgrade) {
		upgrade.setOwner(this);
		this.upgrade = new UpgradeDecorator(upgrade);
	}
	
	@Override
	public void upgrade() throws RuleValidatorException {
		// TODO Auto-generated method stub
		this.upgrade.action();
	}
}
