package org.cx.game.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Map.Entry;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.ActiveSkill;
import org.cx.game.card.skill.ISkill;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IInterceptable;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.Intercepter;
import org.cx.game.intercepter.ProxyFactory;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.out.JsonOut;
import org.cx.game.tools.Logger;
import org.cx.game.widget.Cemetery;
import org.cx.game.widget.ICemetery;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IPlace;

public class Death extends Action implements IDeath {
	
	private Integer hp = 0;
	private Integer status = IDeath.Status_Exist;
	private List<Map<IInterceptable, IIntercepter>> resetList = new ArrayList<Map<IInterceptable, IIntercepter>>();
	
	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return (LifeCard) super.getOwner();
	}
	
	public Integer getHp() {
		return hp;
	}

	/**
	 * 隐式的改变HP
	 * @param hp
	 */
	public void setHp(Integer hp) {
		this.hp = hp;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public void attackToDamage(Integer hp) {
		// TODO Auto-generated method stub
		addToHp(hp);
	}
	
	@Override
	public void magicToHp(Integer hp) {
		// TODO Auto-generated method stub
		addToHp(hp);
	}
	
	/**
	 * 显式的改变HP
	 * @param hp
	 */
	private void addToHp(Integer hp) {
		// TODO Auto-generated method stub
		this.hp += hp;
		this.hp = this.hp>0 ? this.hp : 0;       //判断下限
		this.hp = this.hp<getOwner().getHp() ? this.hp : getOwner().getHp(); //判断上限
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getPlayer());
		map.put("container", getOwner().getContainer());
		map.put("card", getOwner());
		map.put("change", hp);
		map.put("position", getOwner().getContainerPosition());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_State_Hp,map);
		super.notifyObservers(info);
		
		if(0==this.hp){
			try {
				getOwner().death();
			} catch (RuleValidatorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void action(Object...objects) throws RuleValidatorException {
		
		super.action(objects);
		
		this.status = Status_Death;
		
		/*
		 * 当你death时，如果你还有攻击指令没有执行完，即取消攻击；
		 */
		IIntercepter attackIn = new Intercepter(){

			@Override
			public void finish(Object[] args) {
				// TODO Auto-generated method stub
				resetIntercepter();
			}

			@Override
			public Boolean isInvoke() {
				// TODO Auto-generated method stub
				return false;
			}
			
		}; 
		recordIntercepter(getOwner().getAttack(), attackIn);
		
		/*
		 * 当你death时，如果你还有技能指令没有执行完，即取消技能；
		 */
		IIntercepter conjureIn = new Intercepter(){

			@Override
			public void finish(Object[] args) {
				// TODO Auto-generated method stub
				resetIntercepter();
			}

			@Override
			public Boolean isInvoke() {
				// TODO Auto-generated method stub
				return false;
			}
			
		};
		recordIntercepter(getOwner().getConjure(), conjureIn);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getPlayer());
		map.put("container", getOwner().getContainer());
		map.put("card", getOwner());
		map.put("position", getOwner().getContainerPosition());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Action_Death,map);
		super.notifyObservers(info);           //通知所有卡片对象，死亡事件		
		
		getOwner().initState();                //初始化
		
		getOwner().setActivate(false);
		getOwner().getPlayer().getContext().getQueue().remove(getOwner());   //从队列中移除
		
		IGround ground = (IGround)getOwner().getContainer();     //只有在战场上才会死亡
		IPlace place = ground.getPlace(getOwner().getContainerPosition());
		place.out();
		place.getCemetery().add(getOwner());         //进入墓地
		
		LifeCard life = (LifeCard) getOwner();            //停止计算技能冷却时间
		for(ISkill skill : life.getSkillList()){
			if (skill instanceof ActiveSkill) {
				ActiveSkill as = (ActiveSkill) skill;
				life.getPlayer().getContext().deleteIntercepter(as.getCooldownBoutIntercepter());
			}
		}
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
