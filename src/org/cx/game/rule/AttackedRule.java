package org.cx.game.rule;

import org.cx.game.action.Attacked;
import org.cx.game.action.IAttack;
import org.cx.game.action.IAttacked;
import org.cx.game.action.IDeath;
import org.cx.game.card.LifeCard;
import org.cx.game.card.buff.TauntBuff;
import org.cx.game.exception.RuleValidatorException;

public class AttackedRule extends Rule implements IRule {
	
	@Override
	public String getIntercepterMethod() {
		// TODO Auto-generated method stub
		return "action";
	}
	
	@Override
	public void after(Object[] args) {
		// TODO Auto-generated method stub
		LifeCard attackLife = (LifeCard) ((Object[]) args[0])[0];		
		IAttack att = (IAttack) ((Object[]) args[0])[1];
		LifeCard attackedLife = getOwner().getOwner();
	
		if(IDeath.Status_Live.equals(attackedLife.getDeath().getStatus())          //没有死亡 
		&& getOwner().getFightBack()                                           //是否反击过 
		&& !att.getCounterAttack()                                             //这次攻击方式是否是反击
		&& 0<getOwner().getOwner().getAttack().getAtk()                        //是否有攻击力 
		&& getOwner().getOwner().getBuff(TauntBuff.class).isEmpty()){        //没有被嘲讽
			try {
				attackedLife.getAttack().setCounterAttack(true);      //设置为反击
				attackedLife.attack(attackLife);
				attackedLife.getAttack().setCounterAttack(false);     //反击结束
				attackedLife.getAttacked().setFightBack(false);
			} catch (RuleValidatorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public Attacked getOwner() {
		// TODO Auto-generated method stub
		return (Attacked) super.getOwner();
	}
	
	@Override
	public Class getInterceptable() {
		// TODO Auto-generated method stub
		return Attacked.class;
	}

}
