package org.cx.game.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.rule.DeathRule;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IPlace;

public class Death extends Action implements IDeath {
	
	private Integer hp = 0;
	private Integer hplimit = 0;
	private Integer status = IDeath.Status_Exist;
	private List<Map<IInterceptable, IIntercepter>> resetList = new ArrayList<Map<IInterceptable, IIntercepter>>();
	
	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return (LifeCard) super.getOwner();
	}
	
	public Integer getHplimit() {
		return hplimit;
	}

	public void setHplimit(Integer hplimit) {
		this.hplimit = hplimit;
	}

	public Integer getHp() {
		return hp;
	}

	public void setHp(Integer hp) {
		this.hp = hp;
	}
	
	/**
	 * 显式的改变HP
	 * @param hp
	 */
	public void addToHp(Integer hp) {
		// TODO Auto-generated method stub
		if(!Integer.valueOf(0).equals(hp)){
			this.hp += hp;
			this.hp = this.hp>0 ? this.hp : 0;       //判断下限
			this.hp = this.hp<this.hplimit ? this.hp : this.hplimit; //判断上限
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", getOwner().getPlayer());
			map.put("container", getOwner().getContainer());
			map.put("card", getOwner());
			map.put("change", hp);
			map.put("position", getOwner().getContainerPosition());
			NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_State_Hp,map);
			super.notifyObservers(info);
		}
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public void action(Object...objects) throws RuleValidatorException {

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getPlayer());
		map.put("container", getOwner().getContainer());
		map.put("card", getOwner());
		map.put("position", getOwner().getContainerPosition());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Action_Death,map);
		super.notifyObservers(info);           //通知所有卡片对象，死亡事件		
		
		IGround ground = (IGround)getOwner().getContainer();     //只有在战场上才会死亡
		IPlace place = ground.getPlace(getOwner().getContainerPosition());
		place.out();
		place.getCemetery().add(getOwner());         //进入墓地
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
