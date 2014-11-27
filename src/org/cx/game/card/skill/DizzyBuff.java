package org.cx.game.card.skill;

import org.cx.game.card.LifeCard;
import org.cx.game.exception.RuleValidatorException;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.Intercepter;
import org.cx.game.observer.NotifyInfo;
import org.cx.game.widget.IControlQueue;

/**
 * 眩晕
 * @author chenxian
 *
 */
public class DizzyBuff extends Buff {

	public DizzyBuff(Integer bout, LifeCard life) {
		super(bout, IMagic.Style_physical, IBuff.Type_Harm, IMagic.Func_Astrict, life);
		// TODO Auto-generated constructor stub
		setAction(NotifyInfo.Card_LifeCard_Skill_Buff_DizzyBuff);
	}

	@Override
	public void effect() {
		// TODO Auto-generated method stub
		IIntercepter activateIn = new Intercepter("setActivate") {    //当activate状态为true时，表示从眩晕中恢复
			
			@Override
			public void before(Object[] args) {
				// TODO Auto-generated method stub
				if((Boolean)args[0])
					invalid();
			}
		};
		recordIntercepter(getOwner(), activateIn);
		
		affect();
		
		super.effect();
	}
	
	@Override
	public void affect(Object... objects) {
		// TODO Auto-generated method stub
		Integer speed = getOwner().getAttack().getSpeedChance();
		getOwner().getAttack().setSpeedChance(speed-getBout()*IControlQueue.consume);
		
		super.affect(objects);
	}
}
