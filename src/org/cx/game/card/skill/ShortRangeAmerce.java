package org.cx.game.card.skill;

import org.cx.game.action.IAttack;
import org.cx.game.card.LifeCard;
import org.cx.game.widget.IGround;

public class ShortRangeAmerce extends SimplePassiveSkill {

	private Integer atkScale;
	private Integer mode;
	
	public ShortRangeAmerce(Integer style, Integer atkScale, LifeCard life) {
		super(style);
		// TODO Auto-generated constructor stub
		this.atkScale = atkScale;
		setOwner(life);
	}
	
	private Boolean once = true;
	
	@Override
	public void before(Object[] args) {
		// TODO Auto-generated method stub
		if(once){       //只赋值一次，因为后面这个值会被改变
			this.mode = getOwner().getAttack().getMode();
			once = false;
		}
		
		LifeCard attacked = (LifeCard) ((Object[]) args[0])[0];
		
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
		
		if(IAttack.Mode_Far.equals(mode))
			getOwner().getAttack().setMode(IAttack.Mode_Far);
	}
}
