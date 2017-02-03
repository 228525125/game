package org.cx.game.validator;

import org.cx.game.command.CommandBuffer;
import org.cx.game.tools.I18n;

/**
 * 随从攻击力必须大于0才能发起攻击
 * @author chenxian
 *
 */
public class AttackAtkValidator extends SelectLifeCardValidator {

	public AttackAtkValidator(CommandBuffer buffer) {
		super(buffer);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret = super.validate();
		if(ret){
			if(0<getLifeCard().getAttack().getAtk()){
				ret = true;
			}else{
				addMessage(I18n.getMessage(this));
				ret = false;
			}
		}
		
		return ret;
	}
}