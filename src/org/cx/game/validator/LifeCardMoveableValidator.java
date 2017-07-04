package org.cx.game.validator;

import org.cx.game.command.CommandBuffer;
import org.cx.game.tools.I18n;

/**
 * 判断是否可移动
 * @author chenxian
 *
 */
public class LifeCardMoveableValidator extends LifeCardActivateValidator {

	public LifeCardMoveableValidator(CommandBuffer buffer) {
		super(buffer);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret = super.validate();
		if(ret){
			if(getLifeCard().getMove().getMoveable()){
				ret = true;
			}else{
				addMessage(I18n.getMessage(this));
				ret = false;
			}
		}
		return ret;
	}

}
