package org.cx.game.rule;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.cx.game.action.ICall;
import org.cx.game.action.IDeath;
import org.cx.game.card.HeroCard;
import org.cx.game.card.LifeCard;
import org.cx.game.core.IPlayer;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IPlace;

public class CallRule extends Rule implements IRule {
	
	@Override
	public String getIntercepterMethod() {
		// TODO Auto-generated method stub
		return "action";
	}
	
	private Boolean isInvoke = true;
	
	@Override
	public Boolean isInvoke() {
		// TODO Auto-generated method stub
		return isInvoke;
	}
	
	private IPlace place = null;
	private Integer nop = 0;
	
	@Override
	public void before(Object[] args) {
		// TODO Auto-generated method stub
		this.place = (IPlace)((Object[]) args[0])[0];
		this.nop = (Integer)((Object[]) args[0])[1];
		
		ICall call = getOwner();

		LifeCard owner = call.getOwner();
		IGround ground = owner.getPlayer().getGround();
		
		/*
		 * 英雄复活要先把尸体回收
		 */
		if (owner instanceof HeroCard && null!= owner.getContainer()) {
			HeroCard hero = (HeroCard) owner;
			Integer position = hero.getContainerPosition();
			IPlace place = ground.getPlace(position);
			place.getCemetery().remove(hero);
		}
		
		/*
		 * 招募分为创建一支部队和补充兵源，这里处理补充兵源的情况；
		 */
		LifeCard life = place.getLife();
		if(null!=life){
			Integer n = life.getCall().getNop();
			n += this.nop;
			life.getCall().setNop(n);
			
			this.isInvoke = false;
		}
	}
	
	@Override
	public void after(Object[] args) {
		// TODO Auto-generated method stub
		ICall call = getOwner();
		LifeCard owner = call.getOwner();
		
		/*
		 * 刚招募的部队允许反击
		 */
		owner.getDeath().setStatus(IDeath.Status_Live);
		owner.getAttacked().setFightBack(true);
	}
	
	/**
	 * 这里与after区分开，如果是补充兵源after将不会被调用
	 */
	public void finish(Object[] args) {
		// TODO Auto-generated method stub
		ICall call = getOwner();
		LifeCard owner = call.getOwner();
		
		/*
		 * 扣减资源
		 */
		IPlayer player = owner.getPlayer();
		Map<String,Integer> consume = call.getConsume();
		Map<String,Integer> res = new HashMap<String,Integer>();
		for(String key : consume.keySet()){
			Integer value = consume.get(key);
			res.put(key, value*this.nop);
		}
		
		player.addToResource(res);
		
		/*
		 * 计算人口
		 */
		player.addToRation(owner.getRation()*this.nop);
		
		this.isInvoke = true;
	}
	
	@Override
	public ICall getOwner() {
		// TODO Auto-generated method stub
		return (ICall) super.getOwner();
	}

	@Override
	public Class getInterceptable() {
		// TODO Auto-generated method stub
		return ICall.class;
	}

}
