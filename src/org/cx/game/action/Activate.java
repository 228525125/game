package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.rule.ActivateRule;
import org.cx.game.tools.Debug;

public class Activate extends Action implements IActivate {

	private Boolean activation = false;
	private Integer speed = 100;
	private Integer vigour = 0;       //活力，活力为100时，可以行动一次，活力值越多，行动次数越多
	
	@Override
	public Boolean getActivation() {
		// TODO Auto-generated method stub
		return this.activation;
	}

	@Override
	public void setActivation(Boolean activation) {
		// TODO Auto-generated method stub
		this.activation = activation;
	}
	
	@Override
	public Integer getSpeed() {
		// TODO Auto-generated method stub
		return this.speed;
	}
	
	@Override
	public void setSpeed(Integer speed) {
		// TODO Auto-generated method stub
		if(this.speed!=speed){
			this.speed = speed;
		}
	}
	
	@Override
	public void addToSpeed(Integer speed) {
		// TODO Auto-generated method stub
		if(!Integer.valueOf(0).equals(speed)){
			this.speed += speed;
			this.speed = this.speed < 0 ? 0 : this.speed;		
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", getOwner().getPlayer());
			map.put("container", getOwner().getContainer());
			map.put("card", getOwner());
			map.put("change", speed);
			map.put("position", getOwner().getPosition());
			NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_State_Speed,map);
			super.notifyObservers(info);
		}
	}
	
	@Override
	public Integer getVigour() {
		// TODO Auto-generated method stub
		return this.vigour;
	}
	
	@Override
	public void addToVigour(Integer vigour) {
		// TODO Auto-generated method stub
		if(!Integer.valueOf(0).equals(vigour)){
			this.vigour += vigour;
			this.vigour = this.vigour > 200 ? 200 : this.vigour;         //最多一个回合只能行动两次
		}
	}
	
	@Override
	public void action(Object... objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		
		Boolean activate = (Boolean) objects[0];
		
		setActivation(activate);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getPlayer());
		map.put("container", getOwner().getContainer());
		map.put("card", getOwner());
		map.put("position", getOwner().getPosition());
		map.put("activate", activate);
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Action_Activate,map);
		notifyObservers(info);
	}
	
	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return (LifeCard) super.getOwner();
	}

}
