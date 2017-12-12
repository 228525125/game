package org.cx.game.validator;

import org.cx.game.card.ICard;
import org.cx.game.core.IPlayer;
import org.cx.game.tools.I18n;
import org.cx.game.widget.IContainer;

/**
 * item是否属于当前用户
 * @author chenxian
 *
 */
public class OwnerValidator extends Validator {

	private Object item = null;
	private IPlayer curPlayer = null;
	private Boolean isOwner = null;
	
	public OwnerValidator(Object item, IPlayer curPlayer, Boolean isOwner) {
		// TODO Auto-generated constructor stub
		this.item = item;
		this.curPlayer = curPlayer;
		this.isOwner = isOwner;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		Boolean ret = true;
		if (item instanceof IPlayer) {
			IPlayer player = (IPlayer) item;
			if(!isOwner.equals(curPlayer.equals(player))){
				addMessage(I18n.getMessage(OwnerValidator.class.getName()));
				ret = false;
			}
		}
		
		if (item instanceof IContainer) {
			IContainer container = (IContainer) item;
			if(!isOwner.equals(curPlayer.equals(container.getPlayer()))){
				addMessage(I18n.getMessage(OwnerValidator.class.getName()));
				ret = false;
			}
		}
		
		if (item instanceof ICard) {
			ICard card = (ICard) item;
			if(!isOwner.equals(curPlayer.equals(card.getPlayer()))){
				addMessage(I18n.getMessage(OwnerValidator.class.getName()));
				ret = false;
			}
		}
		
		return ret;
	}
}
