package org.cx.game.widget.building;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.action.IUpgrade;
import org.cx.game.action.UpgradeProduct;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.tools.I18n;

public class Product implements IProduct {

	private String name = null;
	private IBuilding building = null;
	private Integer type = 0;
	
	public Product(Integer type) {
		// TODO Auto-generated constructor stub
		this.type = type;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		if(null==name)
			name = I18n.getMessage(this, this.type, "name");
		return name;
	}

	@Override
	public IBuilding getOwner() {
		// TODO Auto-generated method stub
		return building;
	}
	
	@Override
	public void setOwner(IBuilding building) {
		// TODO Auto-generated method stub
		this.building = building;
	}

	@Override
	public Integer getType() {
		// TODO Auto-generated method stub
		return this.type;
	}
	
	private Map<Integer, String> upgradeRequirement = new HashMap<Integer, String>();
	
	public void setUpgradeRequirement(Map<Integer, String> upgradeRequirement) {
		this.upgradeRequirement = upgradeRequirement;
	}
	
	private IUpgrade upgrade = null;
	
	public IUpgrade getUpgrade() {
		if(null==this.upgrade){
			IUpgrade upgrade = new UpgradeProduct(upgradeRequirement);
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
}
