package org.cx.game.policy.formula;

import java.util.List;

import org.cx.game.card.LifeCard;
import org.cx.game.card.buff.AttackLockBuff;
import org.cx.game.card.buff.IBuff;
import org.cx.game.card.skill.AttackLock;
import org.cx.game.tools.I18n;
import org.cx.game.validator.Validator;

public class LockFormula extends Validator implements IFormula {
	
	private LifeCard life = null;
	private List<LifeCard> lockerList = null;

	public LockFormula(LifeCard life) {
		// TODO Auto-generated constructor stub
		this.life = life;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret = false;
		
		if(this.life.containsBuff(AttackLockBuff.class)){
			ret = true;
			List<IBuff> buffList = this.life.getBuff(AttackLockBuff.class);
			for(IBuff buff : buffList){
				AttackLockBuff alb = (AttackLockBuff) buff;
				this.lockerList.add(alb.getLocker());
			}
		}else{
			addMessage(I18n.getMessage(this));
			ret = false;
		}
		
		return ret;
	}
	
	public List<LifeCard> getLockerList(){
		return this.lockerList;
	}
}
