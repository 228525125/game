package org.cx.game.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.card.LifeCard;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.Intercepter;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.widget.IGround;

/**
 * 召唤兽或幻影的死亡方式
 * @author chenxian
 *
 */
public class MirageDeath extends Death {
	
	@Override
	public void action(Object...objects) {
		// TODO Auto-generated method stub
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
		NotifyInfo info = new NotifyInfo(NotifyInfo.Card_LifeCard_Mirage_Action_Death,map);
		super.notifyObservers(info);
		
		getOwner().initState();                //初始化
		
		getOwner().getPlayer().getContext().getQueue().remove(getOwner());   //从队列中移除
		
		IGround ground = (IGround)getOwner().getContainer();     //只有在战场上才会死亡
		ground.remove(getOwner());
	}

}
