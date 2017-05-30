package org.cx.game.validator;

import org.cx.game.command.CommandBuffer;
import org.cx.game.tools.I18n;

public class SelectOptionValidator extends Validator {

	protected CommandBuffer buffer = null;
	
	public SelectOptionValidator(CommandBuffer buffer) {
		// TODO Auto-generated constructor stub
		this.buffer = buffer;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		if(null!=buffer.getOption())
			return true;
		else{
			addMessage(I18n.getMessage(this));
			return false;
		}
	}
}
