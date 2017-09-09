package org.cx.game.widget.building;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.action.IUpgrade;
import org.cx.game.action.Upgrade;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;

public class ProductUpgrade extends Upgrade implements IUpgrade {

	private Integer consume = IUpgrade.BasicConsume;
	
	@Override
	public Integer getConsume() {
		// TODO Auto-generated method stub
		return consume*getLevel();
	}
	
	@Override
	public void setConsume(Integer consume) {
		// TODO Auto-generated method stub
		this.consume = consume;
	}
	
	@Override
	public void action(Object... objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		
		Integer level = getLevel();
		level += 1;
		setLevel(level);
			
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getOwner().getPlayer());
		map.put("container", getOwner().getOwner().getPlayer().getGround());
		map.put("position", getOwner().getOwner().getPosition());
		map.put("building", getOwner().getOwner());
		map.put("productType", getOwner().getType());
		map.put("level", getLevel());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Building_Action_Upgrade_Product,map);
		super.notifyObservers(info);
	}
	
	@Override
	public IProduct getOwner() {
		// TODO Auto-generated method stub
		return (IProduct) super.getOwner();
	}
}
