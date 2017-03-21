package org.cx.game.validator;

import org.cx.game.card.LifeCard;
import org.cx.game.command.CommandBuffer;
import org.cx.game.tools.I18n;

/**
 * 验证选择的LifeCard是否处于潜行状态
 * @author chenxian
 *
 */
public class SelectLifeCardNotHideValidator extends Validator {
	
	private LifeCard attacked = null;
	
	public SelectLifeCardNotHideValidator(LifeCard attacked) {
		// TODO Auto-generated constructor stub
		this.attacked = attacked;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret = super.validate(); 
		if(ret){
			if(this.attacked.getMove().getHide()){
				addMessage(I18n.getMessage(this));
				ret = false;
			}else{
				ret = true;
			}
		}
		
		return ret;
	}

}
