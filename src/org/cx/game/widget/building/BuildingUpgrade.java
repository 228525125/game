package org.cx.game.widget.building;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.action.IUpgrade;
import org.cx.game.action.Upgrade;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.rule.IRule;
import org.cx.game.rule.UpgradeRule;

public class BuildingUpgrade extends Upgrade implements IUpgrade {

	private UpgradeProcess process = null;
	private Integer consume = IUpgrade.BasicConsume;
	
	private IRule rule = new UpgradeRule(this);

	public BuildingUpgrade() {
		// TODO Auto-generated constructor stub
		addObserver(this.rule);
	}
	
	@Override
	public Integer getProcess() {
		// TODO Auto-generated method stub
		return null!=this.process ? this.process.getProcess() : 100;          
	}
	
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
		if(null==this.process && 0<getWaitBout()){
			this.process = new UpgradeProcess(getWaitBout(), this);
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", getOwner().getPlayer());
			map.put("container", getOwner().getPlayer().getGround());
			map.put("position", getOwner().getPosition());
			map.put("building", this);
			map.put("level", getLevel());
			NotifyInfo info = new NotifyInfo(NotifyInfo.Building_Action_Upgrade_Begin,map);
			super.notifyObservers(info);
		}else{
			Integer level = getLevel();
			level += 1;
			setLevel(level);
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", getOwner().getPlayer());
			map.put("container", getOwner().getPlayer().getGround());
			map.put("position", getOwner().getPosition());
			map.put("building", this);
			map.put("level", getLevel());
			NotifyInfo info = new NotifyInfo(NotifyInfo.Building_Action_Upgrade_End,map);
			super.notifyObservers(info);
			
			this.process = null;
		}
	}
	
	@Override
	public IBuilding getOwner() {
		// TODO Auto-generated method stub
		return (IBuilding) super.getOwner();
	}
	
	/**
	 * 反射
	 */
	public void setWaitBout(Integer bout) {
		// TODO Auto-generated method stub
		super.setWaitBout(bout);
	}

}
