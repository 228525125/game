package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.ActiveSkill;
import org.cx.game.card.skill.ISkill;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.widget.IContainer;
import org.cx.game.widget.IPlace;

public class Renew extends Action implements IRenew {

	@Override
	public LifeCard getOwner() {
		// TODO Auto-generated method stub
		return (LifeCard) super.getOwner();
	}
	
	@Override
	public void action(Object...objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		super.action(objects);
		
		IPlace place = (IPlace) objects[0];
		getOwner().initState();		
		getOwner().getDeath().setStatus(Death.Status_Live);
		
		IPlayer player = getOwner().getPlayer();
		IContainer ground = place.getContainer();
		ground.add(place.getPosition(), getOwner());
		
		player.getContext().getQueue().add(getOwner());   //插入队列
						
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("player", getOwner().getPlayer());
		map.put("container", getOwner().getContainer());
		map.put("card", getOwner());
		map.put("position", place.getPosition());
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Action_Renew,map);
		super.notifyObservers(info);           //通知所有卡片对象，召唤事件
		
		LifeCard life = (LifeCard) getOwner();        //启动技能冷却时间的计算
		for(ISkill skill : life.getSkillList()){
			if (skill instanceof ActiveSkill) {
				ActiveSkill as = (ActiveSkill) skill;
				life.getPlayer().getContext().addIntercepter(as.getCooldownBoutIntercepter());
			}
		}
	}
}
