package org.cx.game.action;

import org.cx.game.widget.building.IOption;

public class ExecuteDecorator extends ActionDecorator implements IExecute {

	private IExecute execute = null;
	
	public ExecuteDecorator(IExecute execute) {
		super(execute);
		// TODO Auto-generated constructor stub
		this.execute = execute;
	}

	@Override
	public IOption getOwner() {
		// TODO Auto-generated method stub
		return this.execute.getOwner();
	}
}
