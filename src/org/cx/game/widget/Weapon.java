package org.cx.game.widget;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import org.cx.game.action.IAttack;
import org.cx.game.card.LifeCard;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.JsonOut;

/**
 * 武器
 * @author chenxian
 *
 */
public class Weapon extends Observable implements IWeapon {

	private Integer atk = 0;
	private Integer wear = 0;
	private IAttack attack = null;
	
	/**
	 * 
	 * @param atk 攻击力
	 * @param wear 耐久
	 */
	public Weapon(Integer atk, Integer wear, IAttack attack) {
		// TODO Auto-generated constructor stub
		this.atk = atk;
		this.wear = wear;
		this.attack = attack;
		
		addObserver(new JsonOut());
	}
	
	@Override
	public Integer getAtk() {
		// TODO Auto-generated method stub
		return this.atk;
	}
	
	@Override
	public void setAtk(Integer atk) {
		// TODO Auto-generated method stub
		this.atk = atk;
	}
	
	@Override
	public Integer getWear() {
		// TODO Auto-generated method stub
		return this.wear;
	}

	@Override
	public void setWear(Integer wear) {
		// TODO Auto-generated method stub
		this.wear = wear;
	}
	
	@Override
	public void hand() {
		// TODO Auto-generated method stub
		this.attack.setWeapon(this);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getPlayer());
		map.put("container", getOwner().getContainer());
		map.put("card", getOwner());
		map.put("position", getOwner().getContainerPosition());
		map.put("weapon", this);
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Weapon_Hand,map);
		super.notifyObservers(info);
	}

	@Override
	public Integer output() {
		// TODO Auto-generated method stub
		this.wear -= 1;
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getPlayer());
		map.put("container", getOwner().getContainer());
		map.put("card", getOwner());
		map.put("position", getOwner().getContainerPosition());
		map.put("weapon", this);
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Weapon_Output,map);
		super.notifyObservers(info);
		
		return this.atk;
	}
	
	@Override
	public Boolean isBreakdown() {
		// TODO Auto-generated method stub
		return 0>=this.wear;
	}

	@Override
	public void breakdown() {
		// TODO Auto-generated method stub
		if(Integer.valueOf(0).equals(this.wear)){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", getOwner().getPlayer());
			map.put("container", getOwner().getContainer());
			map.put("card", getOwner());
			map.put("position", getOwner().getContainerPosition());
			map.put("weapon", this);
			NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Weapon_Bearkdown,map);
			super.notifyObservers(info);
			
			attack.setWeapon(null);
		}
	}
	
	@Override
	public void notifyObservers(Object arg) {
		// TODO Auto-generated method stub
		super.setChanged();
		super.notifyObservers(arg);
	}

	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return attack.getOwner();
	}

}
