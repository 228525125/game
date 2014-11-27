package org.cx.game.validator;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.ISkill;
import org.cx.game.command.CommandBuffer;
import org.cx.game.tools.I18n;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IUseCard;

public class QueryCommandValidator extends Validator {

	private String action = null;
	private ICard card = null;
	private ISkill skill = null;
	
	public QueryCommandValidator(String action, CommandBuffer buffer) {
		// TODO Auto-generated constructor stub
		this.action = action;
		this.card = buffer.getCard();
		this.skill = buffer.getSkill();
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		if("call".equals(action)){
			if(card instanceof LifeCard && card.getContainer() instanceof IUseCard)
				return true;
			else
				addMessage(I18n.getMessage(this));
		}
		
		if("attack".equals(action) || "move".equals(action)){
			if(card instanceof LifeCard && card.getContainer() instanceof IGround)
				return true;
			else
				addMessage(I18n.getMessage(this));
		}
		
		if("conjure".equals(action)){
			if(null!=skill)
				return true;
			else
				addMessage(I18n.getMessage(this));
		}
		
		return false;
	}
}
