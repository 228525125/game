package org.cx.game.validator;

import org.cx.game.card.LifeCard;
import org.cx.game.command.CommandBuffer;
import org.cx.game.tools.I18n;

/**
 * 验证缓存中是否存在LifeCard
 * @author chenxian
 *
 */
public class SelectLifeCardValidator extends Validator {
	
	protected CommandBuffer buffer = null;
	private LifeCard life = null;
	
	public SelectLifeCardValidator(CommandBuffer buffer) {
		// TODO Auto-generated constructor stub
		this.buffer = buffer;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret = true; 
		LifeCard life = buffer.getCard(); ;
		
		if(null!=life){
			this.life = life;
			ret = true;
		}else{
			addMessage(I18n.getMessage(SelectLifeCardValidator.class.getName()));
			ret = false;
		}
		
		return ret;
	}

	public LifeCard getLifeCard() {
		return life;
	}
}
