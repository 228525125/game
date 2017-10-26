package org.cx.game.widget;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import org.cx.game.action.IAttack;
import org.cx.game.card.LifeCard;
import org.cx.game.card.magic.WeaponMagicCard;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.JsonOut;
import org.cx.game.rule.RuleGroupFactory;
import org.cx.game.tools.I18n;

/**
 * 武器
 * @author chenxian
 *
 */
public class Weapon extends Observable implements IWeapon {

	private Integer atk = 0;
	private Integer wear = 0;
	private LifeCard hero = null;
	private WeaponMagicCard weaponMagicCard = null;
	
	private String name = null;
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		if(null==name)
			name = I18n.getMessage(this.weaponMagicCard, this.weaponMagicCard.getId(), "name");
		return name;
	}
	
	/**
	 * 
	 * @param atk 攻击力
	 * @param wear 耐久
	 */
	public Weapon(Integer atk, Integer wear, WeaponMagicCard weaponMagicCard, LifeCard hero) {
		// TODO Auto-generated constructor stub
		addObserver(JsonOut.getInstance());
		
		this.atk = atk;
		this.wear = wear;
		this.hero = hero;
		this.weaponMagicCard = weaponMagicCard;
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
	public void addToWear(Integer wear) {
		// TODO Auto-generated method stub
		if(!Integer.valueOf(0).equals(wear)){
			this.wear += wear;
			this.wear = this.wear < 0 ? 0 : this.wear;
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("player", getOwner().getPlayer());
			map.put("container", getOwner().getContainer());
			map.put("card", getOwner());
			map.put("position", getOwner().getContainerPosition());
			map.put("weapon", this);
			map.put("change", wear);
			NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Weapon_Wear,map);
			super.notifyObservers(info);
			
			if(isBreakdown())
				breakdown();
		}
	}

	@Deprecated
	public Integer output() {
		// TODO Auto-generated method stub
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getPlayer());
		map.put("container", getOwner().getContainer());
		map.put("card", getOwner());
		map.put("position", getOwner().getContainerPosition());
		map.put("weapon", this);
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Weapon_Output,map);
		super.notifyObservers(info);
		
		addToWear(-1);
		
		return this.atk;
	}
	
	
	private Boolean isBreakdown() {
		// TODO Auto-generated method stub
		return 0>=this.wear;
	}

	@Override
	public void breakdown() {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getPlayer());
		map.put("container", getOwner().getContainer());
		map.put("card", getOwner());
		map.put("position", getOwner().getContainerPosition());
		map.put("weapon", this);
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Weapon_Bearkdown,map);
		super.notifyObservers(info);
		
		this.hero.getAttack().handWeapon(null);
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
		return this.hero;
	}

	@Override
	public void equip() {
		// TODO Auto-generated method stub
		this.hero.getAttack().handWeapon(this);
	}

}
