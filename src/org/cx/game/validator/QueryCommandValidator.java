package org.cx.game.validator;

import org.cx.game.card.LifeCard;
import org.cx.game.card.skill.ISkill;
import org.cx.game.command.CommandBuffer;
import org.cx.game.tools.I18n;
import org.cx.game.widget.building.IOption;

public class QueryCommandValidator extends Validator {

	private String action = null;
	private LifeCard life = null;
	private ISkill skill = null;
	private IOption option = null;
	
	public QueryCommandValidator(String action, CommandBuffer buffer) {
		// TODO Auto-generated constructor stub
		this.action = action;
		this.life = buffer.getCard();
		this.skill = buffer.getSkill();
		this.option = buffer.getOption();
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		if("attack".equals(action) || "move".equals(action) || "pick".equals(action)){
			if(null!=life.getGround())
				return true;
			else
				addMessage(I18n.getMessage(QueryCommandValidator.class.getName()));
		}
		
		if("conjure".equals(action)){
			if(null!=skill)
				return true;
			else
				addMessage(I18n.getMessage(QueryCommandValidator.class.getName()));
		}
		
		if("apply".equals(action)){
			/*if(card instanceof MagicCard && card.getContainer() instanceof IUseCard)
				return true;
			else
				addMessage(I18n.getMessage(QueryCommandValidator.class.getName()));*/
		}
		
		if("execute".equals(action)){
			if(null!=option)
				return true;
			else
				addMessage(I18n.getMessage(QueryCommandValidator.class.getName()));
		}
		
		return false;
	}
}
