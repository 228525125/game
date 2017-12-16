package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IPlace;

public class Death extends Action implements IDeath {
	
	private Integer hp = 0;
	private Integer hpLimit = 0;
	private Integer extraHp = 0;
	private Integer status = IDeath.Status_Exist;
	
	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return (LifeCard) super.getOwner();
	}

	public Integer getHp() {
		return hp;
	}

	public void setHp(Integer hp) {
		this.hp = hp;
	}
	
	private IAction addToHpAction = null;
	
	public IAction getAddToHpAction() {
		if(null==addToHpAction){
			addToHpAction = new DeathAddToHpAction();
			addToHpAction.setOwner(this);
		}
		return addToHpAction;
	}

	/**
	 * 显式的改变HP
	 * @param hp
	 * @throws RuleValidatorException 
	 */
	public void addToHp(Integer hp) throws RuleValidatorException {
		// TODO Auto-generated method stub
		getAddToHpAction().execute(hp);
	}
	
	public Integer getHpLimit() {
		return hpLimit;
	}

	public void setHpLimit(Integer hpLimit) {
		this.hpLimit = hpLimit;
	}

	public Integer getExtraHp() {
		return extraHp;
	}

	public void setExtraHp(Integer extraHp) {
		this.extraHp = extraHp;
		
		 updateHpLimit();
	}
	
	public void updateHpLimit(){
		Integer level = getOwner().getUpgrade().getLevel();
		Double riseRatio = level>1 ? Math.pow(IUpgrade.DefaultLifeCardRiseRatio, level) * 100 : 100d;
		Integer hp = getOwner().getHp() * riseRatio.intValue() / 100;
		Integer extraHp = getExtraHp();
		this.hpLimit = hp + extraHp;
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
		map.put("position", getOwner().getPosition());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Action_Death,map);
		super.notifyObservers(info);           //通知所有卡片对象，死亡事件		
		
		IGround ground = (IGround)getOwner().getContainer();     //只有在战场上才会死亡
		IPlace place = ground.getPlace(getOwner().getPosition());
		place.out();
		place.getCemetery().add(getOwner());         //进入墓地
	}
	
	public class DeathAddToHpAction extends Action implements IAction {
		
		private Integer damage = 0;
		
		@Override
		public void action(Object... objects) throws RuleValidatorException {
			// TODO Auto-generated method stub
			Integer hp = (Integer) objects[0];
			
			if(!Integer.valueOf(0).equals(hp)){
				Integer before = Death.this.hp;
				
				Death.this.hp += hp;
				Death.this.hp = Death.this.hp>0 ? Death.this.hp : 0;       //判断下限
				Death.this.hp = Death.this.hp<getHpLimit() ? Death.this.hp : getHpLimit(); //判断上限
				
				this.damage = Death.this.hp - before;
				
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("player", getOwner().getOwner().getPlayer());
				map.put("container", getOwner().getOwner().getContainer());
				map.put("card", getOwner().getOwner());
				map.put("change", this.damage);
				map.put("position", getOwner().getOwner().getPosition());
				NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_State_Hp,map);
				super.notifyObservers(info);
			}
		}
		
		public Integer getDamage(){
			return this.damage;
		}
		
		@Override
		public IDeath getOwner() {
			// TODO Auto-generated method stub
			return (IDeath) super.getOwner();
		}
	}
}
