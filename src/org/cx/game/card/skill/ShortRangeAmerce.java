package org.cx.game.card.skill;

import org.cx.game.action.IAttack;
import org.cx.game.card.LifeCard;
import org.cx.game.widget.IGround;

public class ShortRangeAmerce extends SimplePassiveSkill {

	private Integer atkScale;
	
	public ShortRangeAmerce(Integer style, Integer atkScale, LifeCard life) {
		super(style);
		// TODO Auto-generated constructor stub
		this.atkScale = atkScale;
		setOwner(life);
	}
	
	@Override
	public void before(Object[] args) {
		// TODO Auto-generated method stub
		LifeCard attacked = (LifeCard) ((Object[]) args[0])[0];
		Integer mode = getOwner().getAttack().getMode();
		
		IGround ground = getOwner().getPlayer().getGround();
		Integer distance = ground.easyDistance(attacked.getContainerPosition(), getOwner().getContainerPosition());

		if(IAttack.Mode_Far.equals(mode) && 1==distance){
			Integer downAtkValue = getOwner().getAttack().getAtk() * atkScale / 100;
			addToEruptAtk(-downAtkValue);
			getOwner().getAttack().setMode(IAttack.Mode_Near);
			affect();
		}
	}

	@Override
	public void finish(Object[] args) {
		// TODO Auto-generated method stub
		super.finish(args);
		
		getOwner().getAttack().setMode(IAttack.Mode_Far);
	}
}
