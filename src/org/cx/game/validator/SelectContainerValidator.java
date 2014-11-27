package org.cx.game.validator;

import org.cx.game.command.CommandBuffer;
import org.cx.game.tools.I18n;
import org.cx.game.widget.IContainer;

public class SelectContainerValidator extends Validator {

	private IContainer container;
	private CommandBuffer buffer;
	
	public SelectContainerValidator(IContainer container, CommandBuffer buffer) {
		// TODO Auto-generated constructor stub
		this.container = container;
		this.buffer = buffer;
	}
	
	@Override
	public Boolean validate() {
		// TODO Auto-generated method stub
		IContainer selectContainer = buffer.getContainer();
		if(container.equals(selectContainer))
			return true;
		else{
			addMessage(I18n.getMessage(this));
			return false;
		}
	}
}
